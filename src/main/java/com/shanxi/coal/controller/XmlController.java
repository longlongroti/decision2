package com.shanxi.coal.controller;

import com.shanxi.coal.config.MyProperties;
import com.shanxi.coal.dao.*;
import com.shanxi.coal.domain.*;
import com.shanxi.coal.utils.MyDateTimeUtils;
import com.shanxi.coal.utils.MyUtils;
import com.shanxi.coal.utils.ZipUtil;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/xml")
public class XmlController {

    @Resource
    MyProperties myProperties;
    @Resource
    XmlReportMapper xmlReportMapper;
    @Resource
    ManageEventsListMapper manageEventsListMapper;
    @Resource
    ManageEventsDetailsItemMapper manageEventsDetailsItemMapper;

    @Resource
    ManageSystemMapper manageSystemMapper;
    @Resource
    ManageLeaderGroupMapper manageLeaderGroupMapper;
    @Resource
    ManageLeaderMapper manageLeaderMapper;

    public static File packageFileCatalogPwdZip(String p, String fname) throws Exception {
        String catalogPath = p + fname;
        String zipFilePath = p + fname + ".zip";
        File sourceFile = new File(catalogPath);
        ZipFile zipFile = new ZipFile(zipFilePath);
        if (sourceFile.isDirectory()) {
            File[] childrenFiles = sourceFile.listFiles();
            if (childrenFiles != null) {
                for (File f : childrenFiles) {
                    if (f.isDirectory())
                        zipFile.addFolder(new File(f.getAbsolutePath()), ZipUtil.getZipParameters());
                    else
                        zipFile.addFile(f.getAbsolutePath());
                }
            }
        }
        return zipFile.getFile();
    }

    @GetMapping("/send0013")
    public String send0013() throws Exception {
        XMLStaticsParent xmlStaticsParent = new XMLStaticsParent();
        XMLStaticsCompany xmlStaticsCompany = new XMLStaticsCompany();
        xmlStaticsCompany.setCompanyId(myProperties.getCreditCode());
        xmlStaticsCompany.setCompanyName(myProperties.getCompanyName());
        xmlStaticsCompany.setConditionCode("C01");
        xmlStaticsCompany.setConditionCode("C01");
        xmlStaticsCompany.setItemFlag("是");
        xmlStaticsCompany.setDecisionFlag("是");
        xmlStaticsCompany.setManageCode("M01");
        xmlStaticsCompany.setConditionCode("C01");
        xmlStaticsCompany.setStructureCode("S01");
        xmlStaticsCompany.setRegulationCount("0");
        xmlStaticsCompany.setItemCount("0");
        xmlStaticsCompany.setMeetingCount("0");
        xmlStaticsCompany.setSubjectCount("0");
        xmlStaticsCompany.setExceptionCount("0");
        xmlStaticsCompany.setExecutionCount("0");
        xmlStaticsCompany.setRate("0");
        xmlStaticsCompany.setRemark("");
        xmlStaticsCompany.setSource("系统");
        xmlStaticsCompany.setOperType("add");
        xmlStaticsParent.setCompany(xmlStaticsCompany);
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLStaticsParent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String folder = myProperties.getCreditCode() + "_0013_1000_" + MyDateTimeUtils.strNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().replaceAll("-", "");
        String path = myProperties.getXmlPath() + folder;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        jaxbMarshaller.marshal(xmlStaticsParent, new File(path + "/0013_1000_" + MyDateTimeUtils.strNow("yyyyMMdd") + "_0001.xml"));
        File zipFile = packageFileCatalogPwdZip(myProperties.getXmlPath(), folder);
        String s = doSend(zipFile);
        insertReport(s, folder, path, "集团总部所属企业监管统计信息");
        return "manageEventsList/list";
    }

    @PostMapping("/send0014")
    @ResponseBody
    public String send0014(@RequestParam("id") String id) throws Exception {
        ManageSystem manageSystem = manageSystemMapper.selectByPrimaryKey(id);
        XML0014Parent xml0014Parent = new XML0014Parent();
        xml0014Parent.setCompanyId(myProperties.getCreditCode());
        xml0014Parent.setCompanyName(myProperties.getCompanyName());
        xml0014Parent.setEffectiveDate(manageSystem.getEffectiveDate());
        xml0014Parent.setInvalidDate(manageSystem.getExpiryDate());
        xml0014Parent.setRegulationId(manageSystem.getUuid());
        xml0014Parent.setRegulationName(manageSystem.getSystemName());
        xml0014Parent.setSource("系统");
        xml0014Parent.setOperType("add");
        JAXBContext jaxbContext = JAXBContext.newInstance(XML0014Parent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String folder = myProperties.getCreditCode() + "_0014_1000_" + MyDateTimeUtils.strNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().replaceAll("-", "");
        String path = myProperties.getXmlPath() + folder;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        jaxbMarshaller.marshal(xml0014Parent, new File(path + "/0014_1000_" + MyDateTimeUtils.strNow("yyyyMMdd") + "_0001.xml"));
        File zipFile = packageFileCatalogPwdZip(myProperties.getXmlPath(), folder);
        String s = doSend(zipFile);
        insertReport(s, folder, path, "集团所属企业决策制度");
        return "ok";
    }

    @PostMapping("/send")
    @ResponseBody
    public String send(@RequestParam("id") String id) throws Exception {
        List<ManageEventsDetailItem> manageEventsDetailItems = manageEventsDetailsItemMapper.listByParentId(id);
        ManageEventsList manageEventsList = manageEventsListMapper.selectByPrimaryKey(id);
        XMLEventParent xmlParent = new XMLEventParent();
        xmlParent.setCompanyId(myProperties.getCreditCode());
        xmlParent.setCompanyName(myProperties.getCompanyName());
        xmlParent.setRemark("");
        xmlParent.setSource("系统");
        xmlParent.setOperType("add");
        xmlParent.setEffectiveDate(manageEventsList.getStartTime());
        xmlParent.setInvalidDate(manageEventsList.getEndTime());
        xmlParent.setListId(manageEventsList.getUuid());
        xmlParent.setListName(manageEventsList.getListName());
        xmlParent.setListVersion(manageEventsList.getVersionNumber());
        List<XMLEventItem> list = new ArrayList<>();
        for (ManageEventsDetailItem m : manageEventsDetailItems) {
            XMLEventItem xmlEventItem = new XMLEventItem();
            xmlEventItem.setItemCode(m.getEventCode());
            xmlEventItem.setItemId(m.getUuid());
            xmlEventItem.setItemName(m.getEventName());
            xmlEventItem.setLegalFlag(m.getIsLegalReview());
            xmlEventItem.setOperType("add");
            xmlEventItem.setRemark("");
            List<XMLEventItemMeeting> list1 = new ArrayList<>();
            if (StringUtils.isNotBlank(m.getDecisionSequence())) {
                XMLEventItemMeeting xmlEventItemMeeting = new XMLEventItemMeeting();
                xmlEventItemMeeting.setTypeCode(MyUtils.name2code(m.getDecisionSequence()));
                xmlEventItemMeeting.setTypeName(m.getDecisionSequence());
                list1.add(xmlEventItemMeeting);
            } else if (StringUtils.isNotBlank(m.getDecisionSequence2())) {
                XMLEventItemMeeting xmlEventItemMeeting = new XMLEventItemMeeting();
                xmlEventItemMeeting.setTypeCode(MyUtils.name2code(m.getDecisionSequence2()));
                xmlEventItemMeeting.setTypeName(m.getDecisionSequence2());
                list1.add(xmlEventItemMeeting);
            } else if (StringUtils.isNotBlank(m.getDecisionSequence3())) {
                XMLEventItemMeeting xmlEventItemMeeting = new XMLEventItemMeeting();
                xmlEventItemMeeting.setTypeCode(MyUtils.name2code(m.getDecisionSequence3()));
                xmlEventItemMeeting.setTypeName(m.getDecisionSequence3());
                list1.add(xmlEventItemMeeting);
            }
            xmlEventItem.setXmlEventItemMeetings(list1);
            list.add(xmlEventItem);
        }
        xmlParent.setEventItems(list);
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLEventParent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String folder = myProperties.getCreditCode() + "_0004_1000_" + MyDateTimeUtils.strNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().replaceAll("-", "");
        String path = myProperties.getXmlPath() + folder;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        jaxbMarshaller.marshal(xmlParent, new File(path + "/0004_1000_" + MyDateTimeUtils.strNow("yyyyMMdd") + "_0001.xml"));
        File zipFile = packageFileCatalogPwdZip(myProperties.getXmlPath(), folder);
        String s = doSend(zipFile);
        insertReport(s, folder, path, "集团总部事项清单");
        return "ok";
    }

    private void insertReport(String response, String fileName, String path, String type) {
        XmlReport xmlReport = new XmlReport();
        xmlReport.setUuid(UUID.randomUUID().toString());
        xmlReport.setFileName(fileName);
        xmlReport.setUrl(myProperties.getXml0011url());
        xmlReport.setType(type);
        xmlReport.setResponse(response);
        xmlReport.setRemark(path);
        xmlReportMapper.insertSelective(xmlReport);
    }

    private String doSend(File zipFile) {
        String res = "fail";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost(myProperties.getXml0011url());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
            httppost.setConfig(requestConfig);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart("FILE", new FileBody(zipFile));
            builder.addTextBody("USER", "admin");
            builder.addTextBody("PASSWORD", "admin");
            builder.addTextBody("REPAIRMARK", "1");
            httppost.addHeader("filename", zipFile.getName());
            httppost.addHeader("Accept-Charset", "utf-8");
            httppost.setEntity(builder.build());
            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseEntityStr = EntityUtils.toString(response.getEntity());
                    res = responseEntityStr;
                    System.out.println(responseEntityStr);
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            res = e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            res = e.getMessage();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
                res = e.getMessage();
            }
        }
        return res;
    }

    @PostMapping("/sendLeader")
    @ResponseBody
    public String sendLeader(@RequestParam("id") String id) throws Exception {
        List<ManageLeaderGroup> list = manageLeaderGroupMapper.listByUseId(id);
        ManageLeader manageLeader = manageLeaderMapper.selectByPrimaryKey(id);
        XMLParent xmlParent = new XMLParent();
        xmlParent.setCompanyId(myProperties.getCreditCode());
        xmlParent.setCompanyName(myProperties.getCompanyName());
        xmlParent.setRemark("");
        xmlParent.setSource("系统");
        xmlParent.setOperType("add");
        List<XMLGroupList> xmlGroupList = new ArrayList<>();
        List<ManageLeaderGroup> a = new ArrayList<>();
        List<ManageLeaderGroup> b = new ArrayList<>();
        List<ManageLeaderGroup> c = new ArrayList<>();
        List<ManageLeaderGroup> d = new ArrayList<>();
        for (ManageLeaderGroup l : list) {
            if (l.getLeaderGroup().equals("党委(党组)成员(党委会)")
                    || l.getLeaderGroup().equals("党委(党组)成员(党组会)")
                    || l.getLeaderGroup().equals("党委(党组)成员(党委(党组)会)")) {
                a.add(l);
            } else if (l.getLeaderGroup().equals("董事会成员")
                    || l.getLeaderGroup().equals("股东会成员(股东会)")) {
                b.add(l);
            } else if (l.getLeaderGroup().equals("经理班子成员(总经理办公室)")
                    || l.getLeaderGroup().equals("经理班子成员(经理层办公会)")) {
                c.add(l);
            } else if (l.getLeaderGroup().equals("(职代会)") ||
                    l.getLeaderGroup().equals("(其他)")) {
                d.add(l);
            }
        }
        XMLGroupList xmlGroupList1 = new XMLGroupList();
        xmlGroupList1.setGroupType("党委（党组）成员");
        dup(xmlGroupList, a, xmlGroupList1, manageLeader);
        XMLGroupList xmlGroupList2 = new XMLGroupList();
        xmlGroupList2.setGroupType("董事会成员");
        dup(xmlGroupList, b, xmlGroupList2, manageLeader);
        XMLGroupList xmlGroupList3 = new XMLGroupList();
        xmlGroupList3.setGroupType("经理班子成员");
        dup(xmlGroupList, c, xmlGroupList3, manageLeader);
        XMLGroupList xmlGroupList4 = new XMLGroupList();
        xmlGroupList4.setGroupType("其他成员");
        dup(xmlGroupList, d, xmlGroupList4, manageLeader);
        xmlParent.setGroup(xmlGroupList);
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLParent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String folder = myProperties.getCreditCode() + "_0011_1000_" + MyDateTimeUtils.strNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().replaceAll("-", "");
        String path = myProperties.getXmlPath() + folder;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        jaxbMarshaller.marshal(xmlParent, new File(path + "/0011_1000_" + MyDateTimeUtils.strNow("yyyyMMdd") + "_0001.xml"));
        File zipFile = packageFileCatalogPwdZip(myProperties.getXmlPath(), folder);
        String s = doSend(zipFile);
        insertReport(s, folder, path, "集团总部企业基本信息");
        return "ok";
    }

    private void dup(List<XMLGroupList> xmlGroupList, List<ManageLeaderGroup> b, XMLGroupList xmlGroupList2, ManageLeader manageLeader) {
        List<XMLMemberList> xmlMemberLists2 = new ArrayList<>();
        for (ManageLeaderGroup l : b) {
            XMLMemberList xmlMemberList = new XMLMemberList();
            xmlMemberList.setName(manageLeader.getLeaderName());
            xmlMemberList.setEndDate(l.getOfficeEndDate());
            xmlMemberList.setMemberId(manageLeader.getUuid());
            xmlMemberList.setPosition(l.getJobTitle());
            xmlMemberList.setStartDate(l.getOfficeStartDate());
            xmlMemberList.setRemark("");
            xmlMemberList.setOperType("add");
            xmlMemberLists2.add(xmlMemberList);
        }
        xmlGroupList2.setXMLMemberListList(xmlMemberLists2);
        xmlGroupList.add(xmlGroupList2);
    }


}

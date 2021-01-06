package com.shanxi.coal.controller;

import com.shanxi.coal.config.MyProperties;
import com.shanxi.coal.dao.ManageEventsDetailsItemMapper;
import com.shanxi.coal.dao.ManageEventsListMapper;
import com.shanxi.coal.dao.XmlReportMapper;
import com.shanxi.coal.domain.*;
import com.shanxi.coal.utils.MyDateTimeUtils;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping("/send")
    @ResponseBody
    public String delete(@RequestParam("id") String id) throws Exception {
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
                xmlEventItemMeeting.setTypeCode(name2code(m.getDecisionSequence()));
                xmlEventItemMeeting.setTypeName(m.getDecisionSequence());
                list1.add(xmlEventItemMeeting);
            } else if (StringUtils.isNotBlank(m.getDecisionSequence2())) {
                XMLEventItemMeeting xmlEventItemMeeting = new XMLEventItemMeeting();
                xmlEventItemMeeting.setTypeCode(name2code(m.getDecisionSequence2()));
                xmlEventItemMeeting.setTypeName(m.getDecisionSequence2());
                list1.add(xmlEventItemMeeting);
            } else if (StringUtils.isNotBlank(m.getDecisionSequence3())) {
                XMLEventItemMeeting xmlEventItemMeeting = new XMLEventItemMeeting();
                xmlEventItemMeeting.setTypeCode(name2code(m.getDecisionSequence3()));
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
        insertReport(s, folder, path);
        return "ok";
    }

    private String name2code(String decisionSequence) {
        switch (decisionSequence) {
            case "党委会":
                return "DZ";
            case "党组会":
                return "DZ";
            case "总经理办公会":
                return "JL";
            case "党委（党组）会":
                return "DZ";
            case "股东会":
                return "GD";
            case "董事会":
                return "DS";
            case "经理层办公会":
                return "JL";
            case "职代会":
                return "ZD";
            case "其他":
                return "QT";
            default:
                return "";
        }
    }

    private void insertReport(String response, String fileName, String path) {
        XmlReport xmlReport = new XmlReport();
        xmlReport.setUuid(UUID.randomUUID().toString());
        xmlReport.setFileName(fileName);
        xmlReport.setUrl(myProperties.getXml0011url());
        xmlReport.setType("集团总部事项清单");
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }


}

package com.shanxi.coal.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import javax.xml.stream.XMLReporter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
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
    @Resource
    ManageSystemItemsMapper manageSystemItemsMapper;
    @Resource
    ManageMeetingMapper manageMeetingMapper;
    @Resource
    ManageMeetingItemsMapper manageMeetingItemsMapper;
    @Resource
    ManageMeetingAttendeeMapper manageMeetingAttendeeMapper;
    @Resource
    ManageMeetingSubjectMapper manageMeetingSubjectMapper;
    @Resource
    ManageSubjectAttendanceMapper manageSubjectAttendanceMapper;
    @Resource
    ManageSubjectDeliberationMapper manageSubjectDeliberationMapper;
    @Resource
    ManageSubjectItemMapper manageSubjectItemMapper;
    @Resource
    FileUploadedMapper fileUploadedMapper;
    @Resource
    ManageSubjectExecutionMapper manageSubjectExecutionMapper;
    @Resource
    ManageSubjectExecutionDutyMapper manageSubjectExecutionDutyMapper;

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
                        zipFile.addFolder(new File(f.getAbsolutePath()));
                    else
                        zipFile.addFile(f.getAbsolutePath());
                }
            }
        }
        return zipFile.getFile();
    }

    private static void copyFile(String srcPath, String destDir) {
        File srcFile = new File(srcPath);
        if (!srcFile.exists()) { // 源文件不存在
            System.out.println("源文件不存在");
            return;
        }
        // 获取待复制文件的文件名
        String fileName = srcPath
                .substring(srcPath.lastIndexOf(File.separator));
        String destPath = destDir + fileName;
        if (destPath.equals(srcPath)) { // 源文件路径和目标文件路径重复
            System.out.println("源文件路径和目标文件路径重复!");
            return;
        }
        File destFile = new File(destPath);
        if (destFile.exists() && destFile.isFile()) { // 该路径下已经有一个同名文件
            System.out.println("目标目录下已有同名文件!");
            return;
        }
        File destFileDir = new File(destDir);
        destFileDir.mkdirs();
        try {
            FileInputStream fis = new FileInputStream(srcPath);
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024];
            int c;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }
            fis.close();
            fos.close();
        } catch (IOException e) {
        }
    }

    @GetMapping("/go")
    public String go() {
        return "log/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestParam("pageNumber") Integer pageNumber,
                       @RequestParam("pageSize") Integer pageSize) throws ParseException {
        PageHelper.startPage(pageNumber, pageSize);
        XmlReport where = new XmlReport();
        MyUtils.buildCommonWhere(where);
        List<XmlReport> xmlReporters = xmlReportMapper.list(where);
        PageInfo<XmlReport> pageInfo = new PageInfo<XmlReport>(xmlReporters);
        return MyUtils.pageInfoToJson(pageInfo);
    }

    @GetMapping("/send0013")
    public String send0013() throws Exception {
        int count1 = manageSystemMapper.count();
        int count2 = manageEventsDetailsItemMapper.count();
        int count3 = manageMeetingMapper.count();
        int count4 = manageMeetingSubjectMapper.count();
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
        xmlStaticsCompany.setRegulationCount(String.valueOf(count1));
        xmlStaticsCompany.setItemCount(String.valueOf(count2));
        xmlStaticsCompany.setMeetingCount(String.valueOf(count3));
        xmlStaticsCompany.setSubjectCount(String.valueOf(count4));
        xmlStaticsCompany.setExceptionCount("0");
        xmlStaticsCompany.setExecutionCount("100%");
        xmlStaticsCompany.setRate(String.valueOf(count1));
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

    @PostMapping("/send0005")
    @ResponseBody
    public String send0005(@RequestParam("id") String id) throws Exception {
        ManageSystem manageSystem = manageSystemMapper.selectByPrimaryKey(id);
        List<ManageSystemItems> manageSystemItems = manageSystemItemsMapper.listByUseId(id);
        List<FileUploaded> fileUploadeds = fileUploadedMapper.listByCategoryId(id);
        XML0005Parent xml0005Parent = new XML0005Parent();
        xml0005Parent.setCompanyId(myProperties.getCreditCode());
        xml0005Parent.setCompanyName(myProperties.getCompanyName());
        xml0005Parent.setEffectiveDate(manageSystem.getEffectiveDate());
        xml0005Parent.setInvalidDate(manageSystem.getExpiryDate());
        xml0005Parent.setRegulationId(manageSystem.getUuid());
        xml0005Parent.setRegulationName(manageSystem.getSystemName());
        xml0005Parent.setAuditFlag(manageSystem.getIsLegalApprove());
        xml0005Parent.setMeetingTypeCode(MyUtils.name2code(manageSystem.getMeetingType()));
        xml0005Parent.setRegulationTypeName(manageSystem.getSystemType());
        xml0005Parent.setApprovalDate(manageSystem.getApproveDate());
        List<XMLVoteMode> list = new ArrayList<>();
        for (ManageSystemItems i : manageSystemItems) {
            XMLVoteMode xmlVoteMode = new XMLVoteMode();
            xmlVoteMode.setItemCode(i.getItemsName());
            xmlVoteMode.setRate(i.getPeopleCount());
            xmlVoteMode.setVoteMode(i.getVotingFormula());
            list.add(xmlVoteMode);
        }
        xml0005Parent.setXmlVoteModeList(list);
        xml0005Parent.setSource("系统");
        xml0005Parent.setOperType("add");
        JAXBContext jaxbContext = JAXBContext.newInstance(XML0005Parent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String folder = myProperties.getCreditCode() + "_0005_1000_" + MyDateTimeUtils.strNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().replaceAll("-", "");
        String path = myProperties.getXmlPath() + folder;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        File f1 = new File(path + "/正式文件");
        if (!f1.exists()) {
            f1.mkdirs();
        }
        File f2 = new File(path + "/佐证材料");
        if (!f2.exists()) {
            f2.mkdirs();
        }
        for (FileUploaded fs : fileUploadeds) {
            if (fs.getFileCategory().equals("managesystem"))
                copyFile(fs.getFilePath(), f1.getAbsolutePath());
            else if (fs.getFileCategory().equals("managesystemMaterial"))
                copyFile(fs.getFilePath(), f2.getAbsolutePath());
        }
        jaxbMarshaller.marshal(xml0005Parent, new File(path + "/0005_1000_" + MyDateTimeUtils.strNow("yyyyMMdd") + "_0001.xml"));
        File zipFile = packageFileCatalogPwdZip(myProperties.getXmlPath(), folder);
        String s = doSend(zipFile);
        insertReport(s, folder, path, "集团总部决策制度");
        String ret = getResult(s);
        manageSystem.setStatus(ret.equals("ok") ? 99 : 44);
        manageSystemMapper.updateByPrimaryKeySelective(manageSystem);
        return ret;
    }

    @PostMapping("/send0012")
    @ResponseBody
    public String send0012(@RequestParam("id") String id) throws Exception {
//        manageSubjectExecutionMapper.selectByPrimaryKey(id);
//        ManageSystem manageSystem = manageSystemMapper.selectByPrimaryKey(id);
//        List<ManageSystemItems> manageSystemItems = manageSystemItemsMapper.listByUseId(id);
//        List<FileUploaded> fileUploadeds = fileUploadedMapper.listByCategoryId(id);
//        XML0005Parent xml0005Parent = new XML0005Parent();
//        xml0005Parent.setCompanyId(myProperties.getCreditCode());
//        xml0005Parent.setCompanyName(myProperties.getCompanyName());
//        xml0005Parent.setEffectiveDate(manageSystem.getEffectiveDate());
//        xml0005Parent.setInvalidDate(manageSystem.getExpiryDate());
//        xml0005Parent.setRegulationId(manageSystem.getUuid());
//        xml0005Parent.setRegulationName(manageSystem.getSystemName());
//        xml0005Parent.setAuditFlag(manageSystem.getIsLegalApprove());
//        xml0005Parent.setMeetingTypeCode(MyUtils.name2code(manageSystem.getMeetingType()));
//        xml0005Parent.setRegulationTypeName(manageSystem.getSystemType());
//        xml0005Parent.setApprovalDate(manageSystem.getApproveDate());
//        List<XMLVoteMode> list = new ArrayList<>();
//        for (ManageSystemItems i : manageSystemItems) {
//            XMLVoteMode xmlVoteMode = new XMLVoteMode();
//            xmlVoteMode.setItemCode(i.getItemsName());
//            xmlVoteMode.setRate(i.getPeopleCount());
//            xmlVoteMode.setVoteMode(i.getVotingFormula());
//            list.add(xmlVoteMode);
//        }
//        xml0005Parent.setXmlVoteModeList(list);
//        xml0005Parent.setSource("系统");
//        xml0005Parent.setOperType("add");
//        JAXBContext jaxbContext = JAXBContext.newInstance(XML0005Parent.class);
//        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        String folder = myProperties.getCreditCode() + "_0005_1000_" + MyDateTimeUtils.strNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().replaceAll("-", "");
//        String path = myProperties.getXmlPath() + folder;
//        File f = new File(path);
//        if (!f.exists()) {
//            f.mkdirs();
//        }
//        File f1 = new File(path + "/正式文件");
//        if (!f1.exists()) {
//            f1.mkdirs();
//        }
//        File f2 = new File(path + "/佐证材料");
//        if (!f2.exists()) {
//            f2.mkdirs();
//        }
//        for (FileUploaded fs : fileUploadeds) {
//            if (fs.getFileCategory().equals("managesystem"))
//                copyFile(fs.getFilePath(), f1.getAbsolutePath());
//            else if (fs.getFileCategory().equals("managesystemMaterial"))
//                copyFile(fs.getFilePath(), f2.getAbsolutePath());
//        }
//        jaxbMarshaller.marshal(xml0005Parent, new File(path + "/0005_1000_" + MyDateTimeUtils.strNow("yyyyMMdd") + "_0001.xml"));
//        File zipFile = packageFileCatalogPwdZip(myProperties.getXmlPath(), folder);
//        String s = doSend(zipFile);
//        insertReport(s, folder, path, "集团总部决策制度");
        return "ok";
    }

    @PostMapping("/send0006")
    @ResponseBody
    public String send0006(@RequestParam("id") String id) throws Exception {
        ManageMeeting manageMeeting1 = manageMeetingMapper.selectByPrimaryKey(id);
        String host = "";
        if (StringUtils.isNotBlank(manageMeeting1.getCompereId())) {
            ManageLeader manageLeader = manageLeaderMapper.selectByPrimaryKey(manageMeeting1.getCompereId());
            host = manageLeader != null ? manageLeader.getLeaderName() : "";
        }
        List<FileUploaded> fileUploadeds = fileUploadedMapper.listByCategoryId(id);
        XMLMeetingParent xmlMeetingParent = new XMLMeetingParent();
        xmlMeetingParent.setCompanyId(myProperties.getCreditCode());
        xmlMeetingParent.setCompanyName(myProperties.getCompanyName());
        xmlMeetingParent.setMeetingCode(MyUtils.code2code(manageMeeting1.getMeetingType()));
        xmlMeetingParent.setMeetingForm(MyUtils.code2name(manageMeeting1.getMeetingType()));
        xmlMeetingParent.setMeetingId(manageMeeting1.getUuid());
        xmlMeetingParent.setMeetingName(manageMeeting1.getMeetingName());
        xmlMeetingParent.setMeetingTime(manageMeeting1.getMeetingTime());
        xmlMeetingParent.setMeetingTypeCode(manageMeeting1.getMeetingType());
        xmlMeetingParent.setMeetingTypeName(manageMeeting1.getMeetingType());
        xmlMeetingParent.setModerator(host);
        xmlMeetingParent.setReleaseTime(manageMeeting1.getSummaryTime());
        xmlMeetingParent.setSource("系统");
        xmlMeetingParent.setOperType("add");
        List<XMLMeetingAttendee> xmlMeetingAttendees = new ArrayList<>();
        List<ManageMeetingAttendee> manageMeetingAttendees = manageMeetingAttendeeMapper.listByMeetingId(manageMeeting1.getUuid());
        for (ManageMeetingAttendee m : manageMeetingAttendees) {
            XMLMeetingAttendee xmlMeetingAttendee = new XMLMeetingAttendee();
            xmlMeetingAttendee.setAttendeeName(m.getManageLeader() != null ? m.getManageLeader().getLeaderName() : "");
            xmlMeetingAttendee.setReason("");
            xmlMeetingAttendees.add(xmlMeetingAttendee);
        }
        xmlMeetingParent.setXmlMeetingAttendees(xmlMeetingAttendees);
        List<XMLSubjectList> lists = new ArrayList<>();
        List<ManageMeetingSubject> manageMeetingSubjects = manageMeetingSubjectMapper.listByMeetingId(id);
        for (ManageMeetingSubject s : manageMeetingSubjects) {
            List<XMLSubjectRelationList> xmlSubjectRelationLists = new ArrayList<>();
            List<XMLSubjectAttendanceList> xmlSubjectAttendanceLists = new ArrayList<>();
            List<XMLSubjectDeliberationList> xmlSubjectDeliberationLists = new ArrayList<>();
            List<String> code = new ArrayList<>();
            List<ManageSubjectItem> manageSubjectItem = manageSubjectItemMapper.listBySubjectId(s.getUuid());
            for (ManageSubjectItem m : manageSubjectItem) {
                code.add(m.getItemCode());
            }
            List<ManageSubjectAttendance> manageSubjectAttendances = manageSubjectAttendanceMapper.listBySubjectId(s.getUuid());
            for (ManageSubjectAttendance a : manageSubjectAttendances) {
                XMLSubjectAttendanceList xmlSubjectAttendanceList = new XMLSubjectAttendanceList();
                xmlSubjectAttendanceList.setAttendanceName(a.getAttendanceName());
                xmlSubjectAttendanceList.setPosition(a.getPositions());
                xmlSubjectAttendanceLists.add(xmlSubjectAttendanceList);
            }
            List<ManageSubjectDeliberation> manageSubjectDeliberations = manageSubjectDeliberationMapper.listBySubjectId(s.getUuid());
            for (ManageSubjectDeliberation d : manageSubjectDeliberations) {
                XMLSubjectDeliberationList xmlSubjectDeliberationList = new XMLSubjectDeliberationList();
                xmlSubjectDeliberationList.setDeliberationPersonnel(d.getDeliberationPersonnel());
                xmlSubjectDeliberationList.setDeliberationResult(d.getDeliberationResult());
                xmlSubjectDeliberationLists.add(xmlSubjectDeliberationList);
            }
            XMLSubjectList xmlSubjectList = new XMLSubjectList(
                    s.getUuid(),
                    s.getSubjectName(),
                    s.getSubjectCode(),
                    s.getSourceName(),
                    s.getSpecialName(),
                    s.getPassFlag(),
                    s.getApprovalFlag(),
                    s.getAdoptFlag(),
                    s.getSubjectResult(),
                    s.getSuperviseFlag(),
                    s.getRemark(),
                    "add",
                    code,
                    xmlSubjectRelationLists,
                    xmlSubjectAttendanceLists,
                    xmlSubjectDeliberationLists
            );
            lists.add(xmlSubjectList);
        }
        xmlMeetingParent.setXmlSubjectLists(lists);
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLMeetingParent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String folder = myProperties.getCreditCode() + "_0006_1000_" + MyDateTimeUtils.strNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().replaceAll("-", "");
        String path = myProperties.getXmlPath() + folder;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        File f1 = new File(path + "/会议纪要");
        if (!f1.exists()) {
            f1.mkdirs();
        }
        File f2 = new File(path + "/会议通知");
        if (!f2.exists()) {
            f2.mkdirs();
        }
        for (FileUploaded fs : fileUploadeds) {
            if (fs.getFileCategory().equals("meeting"))
                copyFile(fs.getFilePath(), f1.getAbsolutePath());
            else if (fs.getFileCategory().equals("meetingNotice"))
                copyFile(fs.getFilePath(), f2.getAbsolutePath());
        }
        File f3 = new File(path + "/会议议题");
        if (!f3.exists()) {
            f3.mkdirs();
        }
        File f4 = new File(f3.getAbsolutePath() + "/听取意见");
        if (!f4.exists()) {
            f4.mkdirs();
        }
        File f5 = new File(f3.getAbsolutePath() + "/议题材料");
        if (!f5.exists()) {
            f5.mkdirs();
        }
        for (ManageMeetingSubject m : manageMeetingSubjects) {
            List<FileUploaded> fileUploadeds2 = fileUploadedMapper.listByCategoryId(m.getUuid());
            for (FileUploaded fs : fileUploadeds2) {
                if (fs.getFileCategory().equals("subjectAdvice"))
                    copyFile(fs.getFilePath(), f4.getAbsolutePath());
                else if (fs.getFileCategory().equals("subjectMaterial"))
                    copyFile(fs.getFilePath(), f5.getAbsolutePath());
            }
        }
        jaxbMarshaller.marshal(xmlMeetingParent, new File(path + "/0006_1000_" + MyDateTimeUtils.strNow("yyyyMMdd") + "_0001.xml"));
        File zipFile = packageFileCatalogPwdZip(myProperties.getXmlPath(), folder);
        String s = doSend(zipFile);
        insertReport(s, folder, path, "集团总部决策会议");
        String ret = getResult(s);
        manageMeeting1.setStatus(ret.equals("ok") ? 99 : 44);
        manageMeetingMapper.updateByPrimaryKeySelective(manageMeeting1);
        return ret;
    }

    @PostMapping("/send0019")
    @ResponseBody
    public String send0019(@RequestParam("id") String id) throws Exception {
        ManageMeeting manageMeeting1 = manageMeetingMapper.selectByPrimaryKey(id);
        XML0019 xml0019 = new XML0019();
        xml0019.setCompanyId(myProperties.getCreditCode());
        xml0019.setCompanyName(myProperties.getCompanyName());
        xml0019.setMeetingCode(MyUtils.code2code(manageMeeting1.getMeetingType()));
        xml0019.setMeetingId(manageMeeting1.getUuid());
        xml0019.setSource("系统");
        List<XML0019Subject> lists = new ArrayList<>();
        List<ManageMeetingSubject> manageMeetingSubjects = manageMeetingSubjectMapper.listByMeetingId(id);
        for (ManageMeetingSubject s : manageMeetingSubjects) {
            XML0019Subject xml0019Subject = new XML0019Subject();
            xml0019Subject.setSubjectCode(s.getSubjectCode());
            xml0019Subject.setSubjectId(s.getUuid());
            lists.add(xml0019Subject);
        }
        xml0019.setXmlSubjectLists(lists);
        JAXBContext jaxbContext = JAXBContext.newInstance(XML0019.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        String folder = myProperties.getCreditCode() + "_0019_1000_" + MyDateTimeUtils.strNow("yyyyMMddHHmmss") + "_" + UUID.randomUUID().toString().replaceAll("-", "");
        String path = myProperties.getXmlPath() + folder;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        jaxbMarshaller.marshal(xml0019, new File(path + "/0019_1000_" + MyDateTimeUtils.strNow("yyyyMMdd") + "_0001.xml"));
        File zipFile = packageFileCatalogPwdZip(myProperties.getXmlPath(), folder);
        String s = doSend(zipFile);
        insertReport(s, folder, path, "集团及所属企业会议编码及议题编码");
        String ret = getResult(s);
        manageMeeting1.setStatus1(ret.equals("ok") ? 99 : 44);
        manageMeetingMapper.updateByPrimaryKeySelective(manageMeeting1);
        return ret;
    }

    @PostMapping("/send")
    @ResponseBody
    public String send(@RequestParam("id") String id) throws Exception {
        List<ManageEventsDetailItem> manageEventsDetailItems = manageEventsDetailsItemMapper.listByParentId(id);
        ManageEventsList manageEventsList = manageEventsListMapper.selectByPrimaryKey(id);
        List<FileUploaded> fileUploadeds = fileUploadedMapper.listByCategoryId(id);
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
        File f1 = new File(path + "/佐证材料");
        if (!f1.exists()) {
            f1.mkdirs();
        }
        for (FileUploaded fs : fileUploadeds) {
            copyFile(fs.getFilePath(), f1.getAbsolutePath());
        }
        jaxbMarshaller.marshal(xmlParent, new File(path + "/0004_1000_" + MyDateTimeUtils.strNow("yyyyMMdd") + "_0001.xml"));
        File zipFile = packageFileCatalogPwdZip(myProperties.getXmlPath(), folder);
        String s = doSend(zipFile);
        insertReport(s, folder, path, "集团总部事项清单");
        String ret = getResult(s);
        manageEventsList.setStatus(ret.equals("ok") ? 99 : 44);
        manageEventsListMapper.updateByPrimaryKeySelective(manageEventsList);
        return ret;
    }

    private void insertReport(String response, String fileName, String path, String type) {
        XmlReport xmlReport = new XmlReport();
        xmlReport.setUuid(UUID.randomUUID().toString());
        xmlReport.setFileName(fileName);
        xmlReport.setCreatedBy(MyUtils.getSessionUser().getUuid());
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
        String ret = getResult(s);
        manageLeader.setStatus(ret.equals("ok") ? 99 : 44);
        manageLeaderMapper.updateByPrimaryKeySelective(manageLeader);
        return ret;
    }

    private String getResult(String s) {
        Gson g = new Gson();
        JsonObject obj = g.fromJson(s, JsonObject.class);
        Object a = obj.get("serviceFlag");
        return a == null || !((JsonElement) a).getAsString().equals("1") ? "error" : "ok";
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

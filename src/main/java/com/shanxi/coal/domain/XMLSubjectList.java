package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class XMLSubjectList {

    private String subjectId;
    private String subjectName;
    private String subjectCode;
    private String sourceName;
    private String specialName;
    private String passFlag;
    private String approvalFlag;
    private String adoptFlag;
    private String subjectResult;
    private String superviseFlag;
    private String remark;
    private String operType;
    private List<String> itemCodes;
    private List<XMLSubjectRelationList> xmlSubjectRelationListList;
    private List<XMLSubjectAttendanceList> xmlSubjectAttendanceListList;
    private List<XMLSubjectDeliberationList> xmlSubjectDeliberationListList;
    public XMLSubjectList() {
    }
    public XMLSubjectList(String subjectId, String subjectName, String subjectCode, String sourceName, String specialName,
                          String passFlag, String approvalFlag, String adoptFlag, String subjectResult, String superviseFlag,
                          String remark, String operType, List<String> itemCodes,
                          List<XMLSubjectRelationList> xmlSubjectRelationListList,
                          List<XMLSubjectAttendanceList> xmlSubjectAttendanceListList,
                          List<XMLSubjectDeliberationList> xmlSubjectDeliberationListList) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.sourceName = sourceName;
        this.specialName = specialName;
        this.passFlag = passFlag;
        this.approvalFlag = approvalFlag;
        this.adoptFlag = adoptFlag;
        this.subjectResult = subjectResult;
        this.superviseFlag = superviseFlag;
        this.remark = remark;
        this.operType = operType;
        this.itemCodes = itemCodes;
        this.xmlSubjectRelationListList = xmlSubjectRelationListList;
        this.xmlSubjectAttendanceListList = xmlSubjectAttendanceListList;
        this.xmlSubjectDeliberationListList = xmlSubjectDeliberationListList;
    }

    public List<XMLSubjectDeliberationList> getXmlSubjectDeliberationListList() {
        return xmlSubjectDeliberationListList;
    }

    @XmlElementWrapper(name = "deliberation_list")
    @XmlElement(name = "deliberation")
    public void setXmlSubjectDeliberationListList(List<XMLSubjectDeliberationList> xmlSubjectDeliberationListList) {
        this.xmlSubjectDeliberationListList = xmlSubjectDeliberationListList;
    }

    public List<XMLSubjectAttendanceList> getXmlSubjectAttendanceListList() {
        return xmlSubjectAttendanceListList;
    }

    @XmlElementWrapper(name = "attendance_list")
    @XmlElement(name = "attendance")
    public void setXmlSubjectAttendanceListList(List<XMLSubjectAttendanceList> xmlSubjectAttendanceListList) {
        this.xmlSubjectAttendanceListList = xmlSubjectAttendanceListList;
    }

    public List<XMLSubjectRelationList> getXmlSubjectRelationListList() {
        return xmlSubjectRelationListList;
    }

    @XmlElementWrapper(name = "relation_list")
    @XmlElement(name = "relation")
    public void setXmlSubjectRelationListList(List<XMLSubjectRelationList> xmlSubjectRelationListList) {
        this.xmlSubjectRelationListList = xmlSubjectRelationListList;
    }

    public List<String> getItemCodes() {
        return itemCodes;
    }

    @XmlElementWrapper(name = "item_list")
    @XmlElement(name = "item_code")
    public void setItemCodes(List<String> itemCodes) {
        this.itemCodes = itemCodes;
    }

    public String getSubjectId() {
        return subjectId;
    }

    @XmlElement(name = "subject_id")
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    @XmlElement(name = "subject_name")
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    @XmlElement(name = "subject_code")
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSourceName() {
        return sourceName;
    }

    @XmlElement(name = "source_name")
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSpecialName() {
        return specialName;
    }

    @XmlElement(name = "special_name")
    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public String getPassFlag() {
        return passFlag;
    }

    @XmlElement(name = "pass_flag")
    public void setPassFlag(String passFlag) {
        this.passFlag = passFlag;
    }

    public String getApprovalFlag() {
        return approvalFlag;
    }

    @XmlElement(name = "approval_flag")
    public void setApprovalFlag(String approvalFlag) {
        this.approvalFlag = approvalFlag;
    }

    public String getAdoptFlag() {
        return adoptFlag;
    }

    @XmlElement(name = "adopt_flag")
    public void setAdoptFlag(String adoptFlag) {
        this.adoptFlag = adoptFlag;
    }

    public String getSubjectResult() {
        return subjectResult;
    }

    @XmlElement(name = "subject_result")
    public void setSubjectResult(String subjectResult) {
        this.subjectResult = subjectResult;
    }

    public String getSuperviseFlag() {
        return superviseFlag;
    }

    @XmlElement(name = "supervise_flag")
    public void setSuperviseFlag(String superviseFlag) {
        this.superviseFlag = superviseFlag;
    }

    public String getRemark() {
        return remark;
    }

    @XmlElement(name = "remark")
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperType() {
        return operType;
    }

    @XmlElement(name = "oper_type")
    public void setOperType(String operType) {
        this.operType = operType;
    }
}

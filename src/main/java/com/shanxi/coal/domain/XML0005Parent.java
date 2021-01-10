package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tiol")
public class XML0005Parent {

    private String regulationId;
    private String companyName;
    private String companyId;
    private String regulationName;
    private String effectiveDate;
    private String invalidDate;
    private String auditFlag;
    private String source;
    private String operType;
    private String meetingTypeCode;
    private String regulationTypeName;
    private String approvalDate;
    private String remark;
    private List<XMLVoteMode> xmlVoteModeList;

    public List<XMLVoteMode> getXmlVoteModeList() {
        return xmlVoteModeList;
    }
    @XmlElementWrapper(name="vote_mode_list")
    @XmlElement(name = "vote_mode")
    public void setXmlVoteModeList(List<XMLVoteMode> xmlVoteModeList) {
        this.xmlVoteModeList = xmlVoteModeList;
    }

    public String getRegulationTypeName() {
        return regulationTypeName;
    }
    @XmlElement(name = "regulation_type_name")
    public void setRegulationTypeName(String regulationTypeName) {
        this.regulationTypeName = regulationTypeName;
    }

    public String getRemark() {
        return remark;
    }
    @XmlElement(name = "remark")
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMeetingTypeCode() {
        return meetingTypeCode;
    }
    @XmlElement(name = "meeting_type_code")
    public void setMeetingTypeCode(String meetingTypeCode) {
        this.meetingTypeCode = meetingTypeCode;
    }

    public String getApprovalDate() {
        return approvalDate;
    }
    @XmlElement(name = "approval_date")
    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getAuditFlag() {
        return auditFlag;
    }
    @XmlElement(name = "audit_flag")
    public void setAuditFlag(String auditFlag) {
        this.auditFlag = auditFlag;
    }

    public String getRegulationId() {
        return regulationId;
    }

    @XmlElement(name = "regulation_id")
    public void setRegulationId(String regulationId) {
        this.regulationId = regulationId;
    }

    public String getCompanyName() {
        return companyName;
    }

    @XmlElement(name = "company_name")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    @XmlElement(name = "company_id")
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRegulationName() {
        return regulationName;
    }

    @XmlElement(name = "regulation_name")
    public void setRegulationName(String regulationName) {
        this.regulationName = regulationName;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    @XmlElement(name = "effective_date")
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    @XmlElement(name = "invalid_date")
    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getSource() {
        return source;
    }

    @XmlElement(name = "source")
    public void setSource(String source) {
        this.source = source;
    }

    public String getOperType() {
        return operType;
    }

    @XmlElement(name = "oper_type")
    public void setOperType(String operType) {
        this.operType = operType;
    }
}

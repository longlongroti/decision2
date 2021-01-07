package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;

public class XMLStaticsCompany {
    private String companyName;
    private String companyId;
    private String itemFlag;
    private String decisionFlag;
    private String manageCode;
    private String conditionCode;
    private String structureCode;
    private String regulationCount;
    private String itemCount;
    private String meetingCount;
    private String subjectCount;
    private String exceptionCount;
    private String executionCount;
    private String rate;
    private String remark;
    private String source;
    private String operType;

    public String getRate() {
        return rate;
    }
    @XmlElement(name = "rate")
    public void setRate(String rate) {
        this.rate = rate;
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

    public String getItemFlag() {
        return itemFlag;
    }

    @XmlElement(name = "item_flag")
    public void setItemFlag(String itemFlag) {
        this.itemFlag = itemFlag;
    }

    public String getDecisionFlag() {
        return decisionFlag;
    }

    @XmlElement(name = "decision_flag")
    public void setDecisionFlag(String decisionFlag) {
        this.decisionFlag = decisionFlag;
    }

    public String getManageCode() {
        return manageCode;
    }

    @XmlElement(name = "manage_code")
    public void setManageCode(String manageCode) {
        this.manageCode = manageCode;
    }

    public String getConditionCode() {
        return conditionCode;
    }

    @XmlElement(name = "condition_code")
    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    public String getStructureCode() {
        return structureCode;
    }

    @XmlElement(name = "structure_code")
    public void setStructureCode(String structureCode) {
        this.structureCode = structureCode;
    }

    public String getRegulationCount() {
        return regulationCount;
    }
    @XmlElement(name = "regulation_count")
    public void setRegulationCount(String regulationCount) {
        this.regulationCount = regulationCount;
    }


    public String getItemCount() {
        return itemCount;
    }

    @XmlElement(name = "item_count")
    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getMeetingCount() {
        return meetingCount;
    }

    @XmlElement(name = "meeting_count")
    public void setMeetingCount(String meetingCount) {
        this.meetingCount = meetingCount;
    }

    public String getSubjectCount() {
        return subjectCount;
    }

    @XmlElement(name = "subject_count")
    public void setSubjectCount(String subjectCount) {
        this.subjectCount = subjectCount;
    }

    public String getExceptionCount() {
        return exceptionCount;
    }

    @XmlElement(name = "exception_count")
    public void setExceptionCount(String exceptionCount) {
        this.exceptionCount = exceptionCount;
    }

    public String getExecutionCount() {
        return executionCount;
    }

    @XmlElement(name = "execution_count")
    public void setExecutionCount(String executionCount) {
        this.executionCount = executionCount;
    }

    public String getRemark() {
        return remark;
    }

    @XmlElement(name = "remark")
    public void setRemark(String remark) {
        this.remark = remark;
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

package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tiol")
public class XML0012 {

    private String executionId;
    private String companyName;
    private String companyId;
    private String subjectCode;
    private String source;
    private String effect;
    private String status;
    private String description;
    private String remark;
    private String operType;
    private List<XML0012Duty> xml0012Duties;

    public String getExecutionId() {
        return executionId;
    }
    @XmlElement(name = "execution_id")
    public void setExecutionId(String executionId) {
        this.executionId = executionId;
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

    public String getSubjectCode() {
        return subjectCode;
    }
    @XmlElement(name = "subject_code")
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSource() {
        return source;
    }
    @XmlElement(name = "source")
    public void setSource(String source) {
        this.source = source;
    }

    public String getEffect() {
        return effect;
    }
    @XmlElement(name = "effect")
    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getStatus() {
        return status;
    }
    @XmlElement(name = "status")
    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }
    @XmlElement(name = "description")
    public void setDescription(String description) {
        this.description = description;
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

    public List<XML0012Duty> getXml0012Duties() {
        return xml0012Duties;
    }
    @XmlElementWrapper(name="duty_list")
    @XmlElement(name = "duty")
    public void setXml0012Duties(List<XML0012Duty> xml0012Duties) {
        this.xml0012Duties = xml0012Duties;
    }
}

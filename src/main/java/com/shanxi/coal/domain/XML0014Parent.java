package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tiol")
public class XML0014Parent {

    private String regulationId;
    private String companyName;
    private String companyId;
    private String regulationName;
    private String effectiveDate;
    private String invalidDate;
    private String source;
    private String operType;

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

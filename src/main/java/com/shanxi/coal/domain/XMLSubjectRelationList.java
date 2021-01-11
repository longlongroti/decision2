package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;

public class XMLSubjectRelationList {
    private String relCompanyId;
    private String relSubjectCode;

    public String getRelCompanyId() {
        return relCompanyId;
    }
    @XmlElement(name = "rel_company_id")
    public void setRelCompanyId(String relCompanyId) {
        this.relCompanyId = relCompanyId;
    }

    public String getRelSubjectCode() {
        return relSubjectCode;
    }
    @XmlElement(name = "rel_subject_code")
    public void setRelSubjectCode(String relSubjectCode) {
        this.relSubjectCode = relSubjectCode;
    }
}

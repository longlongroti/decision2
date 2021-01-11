package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;

public class XMLSubjectDeliberationList {
    private String deliberationPersonnel;
    private String deliberationResult;

    public String getDeliberationPersonnel() {
        return deliberationPersonnel;
    }
    @XmlElement(name = "deliberation_personnel")
    public void setDeliberationPersonnel(String deliberationPersonnel) {
        this.deliberationPersonnel = deliberationPersonnel;
    }

    public String getDeliberationResult() {
        return deliberationResult;
    }
    @XmlElement(name = "deliberation_result")
    public void setDeliberationResult(String deliberationResult) {
        this.deliberationResult = deliberationResult;
    }
}

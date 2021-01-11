package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;

public class XML0019Subject {
    private String subjectId;
    private String subjectCode;

    public String getSubjectId() {
        return subjectId;
    }
    @XmlElement(name = "subject_id")
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }
    @XmlElement(name = "subject_code")
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
}

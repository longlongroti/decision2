package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tiol")
public class XMLStaticsParent {

    private XMLStaticsCompany company;

    public XMLStaticsCompany getCompany() {
        return company;
    }
    @XmlElement(name = "company")
    public void setCompany(XMLStaticsCompany company) {
        this.company = company;
    }
}

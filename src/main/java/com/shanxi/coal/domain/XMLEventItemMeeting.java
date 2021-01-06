package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;

public class XMLEventItemMeeting {
    private String typeName;
    private String typeCode;

    public String getTypeName() {
        return typeName;
    }
    @XmlElement(name = "type_name")
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }
    @XmlElement(name = "type_code")
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}

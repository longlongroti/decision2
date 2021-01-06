package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

public class XMLEventItem {
    private String itemId;
    private String itemCode;
    private String itemName;
    private String legalFlag;
    private String remark;
    private String operType;

    private List<XMLEventItemMeeting> xmlEventItemMeetings;

    public String getItemId() {
        return itemId;
    }
    @XmlElement(name = "item_id")
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }
    @XmlElement(name = "item_code")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }
    @XmlElement(name = "item_name")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLegalFlag() {
        return legalFlag;
    }
    @XmlElement(name = "legal_flag")
    public void setLegalFlag(String legalFlag) {
        this.legalFlag = legalFlag;
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

    public List<XMLEventItemMeeting> getXmlEventItemMeetings() {
        return xmlEventItemMeetings;
    }
    @XmlElementWrapper(name="item_meeting_list")
    @XmlElement(name = "item_meeting")
    public void setXmlEventItemMeetings(List<XMLEventItemMeeting> xmlEventItemMeetings) {
        this.xmlEventItemMeetings = xmlEventItemMeetings;
    }
}

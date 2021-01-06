package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tiol")
public class XMLEventParent {

    private String listId;
    private String companyName;
    private String companyId;
    private String listName;
    private String listVersion;
    private String effectiveDate;
    private String invalidDate;
    private String remark;
    private String operType;
    private String source;

    public String getListId() {
        return listId;
    }
    @XmlElement(name = "list_id")
    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }
    @XmlElement(name = "list_name")
    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListVersion() {
        return listVersion;
    }
    @XmlElement(name = "list_version")
    public void setListVersion(String listVersion) {
        this.listVersion = listVersion;
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

    public String getSource() {
        return source;
    }
    @XmlElement(name = "source")
    public void setSource(String source) {
        this.source = source;
    }
    private List<XMLEventItem> eventItems;

    public List<XMLEventItem> getEventItems() {
        return eventItems;
    }
    @XmlElementWrapper(name="item_list")
    @XmlElement(name = "item")
    public void setEventItems(List<XMLEventItem> eventItems) {
        this.eventItems = eventItems;
    }




}

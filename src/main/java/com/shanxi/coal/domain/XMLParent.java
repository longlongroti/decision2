package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tiol")
public class XMLParent {

    private String companyName;
    private String companyId;
    private String remark;
    private String operType;
    private String source;

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
//
    private List<XMLGroupList> group;

    public List<XMLGroupList> getGroup() {
        return group;
    }
    @XmlElementWrapper(name="group_list")
    @XmlElement(name = "group")
    public void setGroup(List<XMLGroupList> group) {
        this.group = group;
    }

}

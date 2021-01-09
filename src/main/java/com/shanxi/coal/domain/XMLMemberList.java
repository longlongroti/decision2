package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="member")
public class XMLMemberList {
    private String memberId;
    private String name;
    private String position;
    private String startDate;
    private String endDate;
    private String remark;
    private String operType;

    public String getMemberId() {
        return memberId;
    }
    @XmlElement(name = "member_id")
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }
    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }
    @XmlElement(name = "position")
    public void setPosition(String position) {
        this.position = position;
    }

    public String getStartDate() {
        return startDate;
    }
    @XmlElement(name = "start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    @XmlElement(name = "end_date")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
}

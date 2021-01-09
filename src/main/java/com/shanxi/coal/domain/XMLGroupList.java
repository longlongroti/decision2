package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="group")
public class XMLGroupList {
    private String groupType;

    public String getGroupType() {
        return groupType;
    }
    @XmlElement(name = "group_type")
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    private List<XMLMemberList> XMLMemberListList;

    public List<XMLMemberList> getXMLMemberListList() {
        return XMLMemberListList;
    }
    @XmlElementWrapper(name="member_list")
    @XmlElement(name = "member")
    public void setXMLMemberListList(List<XMLMemberList> XMLMemberListList) {
        this.XMLMemberListList = XMLMemberListList;
    }
}

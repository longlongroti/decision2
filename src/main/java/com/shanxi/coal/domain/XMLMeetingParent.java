package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tiol")
public class XMLMeetingParent {

    private String meetingId;
    private String companyName;
    private String companyId;
    private String meetingName;
    private String meetingCode;
    private String meetingTypeName;
    private String meetingTypeCode;
    private String meetingForm;
    private String meetingTime;
    private String releaseTime;
    private String moderator;
    private String remark;
    private String source;
    private String operType;
    private List<XMLMeetingAttendee> xmlMeetingAttendees;
    private List<XMLSubjectList> xmlSubjectLists;

    public String getMeetingId() {
        return meetingId;
    }

    @XmlElement(name = "meeting_id")
    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
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

    public String getMeetingName() {
        return meetingName;
    }

    @XmlElement(name = "meeting_name")
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingCode() {
        return meetingCode;
    }

    @XmlElement(name = "meeting_code")
    public void setMeetingCode(String meetingCode) {
        this.meetingCode = meetingCode;
    }

    public String getMeetingTypeName() {
        return meetingTypeName;
    }

    @XmlElement(name = "meeting_type_name")
    public void setMeetingTypeName(String meetingTypeName) {
        this.meetingTypeName = meetingTypeName;
    }

    public String getMeetingTypeCode() {
        return meetingTypeCode;
    }

    @XmlElement(name = "meeting_type_code")
    public void setMeetingTypeCode(String meetingTypeCode) {
        this.meetingTypeCode = meetingTypeCode;
    }

    public String getMeetingForm() {
        return meetingForm;
    }

    @XmlElement(name = "meeting_form")
    public void setMeetingForm(String meetingForm) {
        this.meetingForm = meetingForm;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    @XmlElement(name = "meeting_time")
    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    @XmlElement(name = "release_time")
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getModerator() {
        return moderator;
    }

    @XmlElement(name = "moderator")
    public void setModerator(String moderator) {
        this.moderator = moderator;
    }

    public String getRemark() {
        return remark;
    }

    @XmlElement(name = "remark")
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSource() {
        return source;
    }

    @XmlElement(name = "source")
    public void setSource(String source) {
        this.source = source;
    }

    public String getOperType() {
        return operType;
    }

    @XmlElement(name = "oper_type")
    public void setOperType(String operType) {
        this.operType = operType;
    }

    public List<XMLMeetingAttendee> getXmlMeetingAttendees() {
        return xmlMeetingAttendees;
    }
    @XmlElementWrapper(name="attendee_list")
    @XmlElement(name = "attendee")
    public void setXmlMeetingAttendees(List<XMLMeetingAttendee> xmlMeetingAttendees) {
        this.xmlMeetingAttendees = xmlMeetingAttendees;
    }

    public List<XMLSubjectList> getXmlSubjectLists() {
        return xmlSubjectLists;
    }
    @XmlElementWrapper(name="subject_list")
    @XmlElement(name = "subject")
    public void setXmlSubjectLists(List<XMLSubjectList> xmlSubjectLists) {
        this.xmlSubjectLists = xmlSubjectLists;
    }
}

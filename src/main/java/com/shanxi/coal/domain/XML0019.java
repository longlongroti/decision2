package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tiol")
public class XML0019 {

    private String meetingId;
    private String companyName;
    private String companyId;
    private String meetingCode;
    private String source;
    private List<XML0019Subject> xml0019Subjects;

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


    public String getMeetingCode() {
        return meetingCode;
    }

    @XmlElement(name = "meeting_code")
    public void setMeetingCode(String meetingCode) {
        this.meetingCode = meetingCode;
    }


    public String getSource() {
        return source;
    }

    @XmlElement(name = "source")
    public void setSource(String source) {
        this.source = source;
    }


    public List<XML0019Subject> getXmlSubjectLists() {
        return xml0019Subjects;
    }
    @XmlElementWrapper(name="subject_list")
    @XmlElement(name = "subject")
    public void setXmlSubjectLists(List<XML0019Subject> xml0019Subjects) {
        this.xml0019Subjects = xml0019Subjects;
    }
}

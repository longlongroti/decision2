package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;

public class XMLMeetingAttendee {
    private String attendeeName;
    private String reason;

    public String getAttendeeName() {
        return attendeeName;
    }
    @XmlElement(name = "attendee_name")
    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }

    public String getReason() {
        return reason;
    }
    @XmlElement(name = "reason")
    public void setReason(String reason) {
        this.reason = reason;
    }
}

package com.shanxi.coal.domain;

import javax.xml.bind.annotation.XmlElement;

public class XMLSubjectAttendanceList {
    private String attendanceName;
    private String position;

    public String getAttendanceName() {
        return attendanceName;
    }
    @XmlElement(name = "attendance_name")
    public void setAttendanceName(String attendanceName) {
        this.attendanceName = attendanceName;
    }

    public String getPosition() {
        return position;
    }
    @XmlElement(name = "position")
    public void setPosition(String position) {
        this.position = position;
    }
}

package com.shanxi.coal.domain;

import java.io.Serializable;

public class ManageMeetingItems extends CommonBean implements Serializable {
    private String parentId;

    private String eventCode;

    private String pass;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
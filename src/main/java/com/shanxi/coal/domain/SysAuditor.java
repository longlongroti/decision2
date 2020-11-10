package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.List;

public class SysAuditor extends CommonBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;
    private String auditName;
    private String userId;
    private String orgName;
    private String orgUUid;
    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgUUid() {
        return orgUUid;
    }

    public void setOrgUUid(String orgUUid) {
        this.orgUUid = orgUUid;
    }

}

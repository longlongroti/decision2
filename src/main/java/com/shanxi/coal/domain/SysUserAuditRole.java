package com.shanxi.coal.domain;

import java.io.Serializable;

public class SysUserAuditRole extends CommonBean implements Serializable {

    private String auditRoleId;
    private String auditRoleName;

    private String userId;

    private static final long serialVersionUID = 1L;

    public String getAuditRoleName() {
        return auditRoleName;
    }

    public void setAuditRoleName(String auditRoleName) {
        this.auditRoleName = auditRoleName;
    }

    public String getAuditRoleId() {
        return auditRoleId;
    }

    public void setAuditRoleId(String auditRoleId) {
        this.auditRoleId = auditRoleId == null ? null : auditRoleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}

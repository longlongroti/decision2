package com.shanxi.coal.domain;

import java.io.Serializable;

public class SysUserDataRole extends CommonBean  implements Serializable {

    private String roleId;

    private String userId;

    private SysDataRole sysDataRole;

    public SysDataRole getSysDataRole() {
        return sysDataRole;
    }

    public void setSysDataRole(SysDataRole sysDataRole) {
        this.sysDataRole = sysDataRole;
    }

    private static final long serialVersionUID = 1L;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}

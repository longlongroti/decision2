package com.shanxi.coal.domain;

import java.io.Serializable;

public class SysRolePower extends CommonBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roleId;
    private String power;

    public SysRolePower() {
    }

    public SysRolePower(String roleId, String power, String uuid, Integer status) {
        super.setUuid(uuid);
        this.roleId = roleId;
        this.power = power;
        super.setStatus(status);
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }
}

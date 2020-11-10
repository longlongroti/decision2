package com.shanxi.coal.domain;

import java.io.Serializable;

public class SysDataRolePower extends CommonBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String roleId;
    private String power;
    private String op;
    private String var;

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public SysDataRolePower() {
    }

    public SysDataRolePower(String roleId,String op,String var, String power, String uuid, Integer status) {
        super.setUuid(uuid);
        this.roleId = roleId;
        this.power = power;
        this.op = op;
        this.var = var;
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

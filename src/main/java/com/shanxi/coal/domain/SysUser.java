package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.List;

public class SysUser extends CommonBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private String name;
    private String orgId;//所属部门
    private String userIdentity;
    private String email;
    private String address;
    private String phone;
    private String fax;
    private String code;
    private String roles;
    private Boolean specialAuth;
    private Boolean isOrgBoss;
    private String auditRoles;
    private String dataRoles;
    private SysOrg userOrg;//所属部门信息
    private String companyId;//矿级
    private String rentCompanyId;//对应租赁公司
    private String parentCompanyId;//对应二级单位
    private List<SysUserRole> sysUserRoles;
    private List<SysUserDataRole> sysUserDataRoles;
    private List<SysUserAuditRole> sysUserAuditRoles;
    private SysOrg userCompany;//矿级信息
    private SysOrg userParentCompany;//二级单位信息
    private SysOrg userRentCompany;//租赁公司信息

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRentCompanyId() {
        return rentCompanyId;
    }

    public void setRentCompanyId(String rentCompanyId) {
        this.rentCompanyId = rentCompanyId;
    }

    public String getParentCompanyId() {
        return parentCompanyId;
    }

    public void setParentCompanyId(String parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }

    public SysOrg getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(SysOrg userOrg) {
        this.userOrg = userOrg;
    }

    public List<SysUserRole> getSysUserRoles() {
        return sysUserRoles;
    }

    public void setSysUserRoles(List<SysUserRole> sysUserRoles) {
        this.sysUserRoles = sysUserRoles;
    }

    public List<SysUserDataRole> getSysUserDataRoles() {
        return sysUserDataRoles;
    }

    public void setSysUserDataRoles(List<SysUserDataRole> sysUserDataRoles) {
        this.sysUserDataRoles = sysUserDataRoles;
    }

    public List<SysUserAuditRole> getSysUserAuditRoles() {
        return sysUserAuditRoles;
    }

    public void setSysUserAuditRoles(List<SysUserAuditRole> sysUserAuditRoles) {
        this.sysUserAuditRoles = sysUserAuditRoles;
    }

    public String getDataRoles() {
        return dataRoles;
    }

    public void setDataRoles(String dataRoles) {
        this.dataRoles = dataRoles;
    }

    public Boolean getOrgBoss() {
        return isOrgBoss;
    }

    public void setOrgBoss(Boolean orgBoss) {
        isOrgBoss = orgBoss;
    }

    public Boolean getSpecialAuth() {
        return specialAuth;
    }

    public void setSpecialAuth(Boolean specialAuth) {
        this.specialAuth = specialAuth;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAuditRoles() {
        return auditRoles;
    }

    public void setAuditRoles(String auditRoles) {
        this.auditRoles = auditRoles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity == null ? null : userIdentity.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public SysOrg getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(SysOrg userCompany) {
        this.userCompany = userCompany;
    }

    public SysOrg getUserParentCompany() {
        return userParentCompany;
    }

    public void setUserParentCompany(SysOrg userParentCompany) {
        this.userParentCompany = userParentCompany;
    }

    public SysOrg getUserRentCompany() {
        return userRentCompany;
    }

    public void setUserRentCompany(SysOrg userRentCompany) {
        this.userRentCompany = userRentCompany;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", orgId='" + orgId + '\'' +
                ", userIdentity='" + userIdentity + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", code='" + code + '\'' +
                ", roles='" + roles + '\'' +
                ", auditRoles='" + auditRoles + '\'' +
                '}';
    }
}

package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SysOrg extends CommonBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String code;
    private String address;
    private String phone;
    private String email;
    private String fax;
    private String parent;
    private String text;
    private String id;
    private String shortName;
    private String companyId;
    private String storageOrg;
    private List<Integer> tags;
    private List<SysOrg> nodes;
    private List<SysOrg> children;
    private SysOrg parentOrg;
    private TreeNodeState state;
    private String isRentOrg;
    private String isTwoLevel;

    public List<SysOrg> getChildren() {
        return children;
    }

    public void setChildren(List<SysOrg> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TreeNodeState getState() {
        return state;
    }

    public void setState(TreeNodeState state) {
        this.state = state;
    }

    public String getStorageOrg() {
        return storageOrg;
    }

    public void setStorageOrg(String storageOrg) {
        this.storageOrg = storageOrg;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public SysOrg getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(SysOrg parentOrg) {
        this.parentOrg = parentOrg;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public List<SysOrg> getNodes() {
        return nodes;
    }

    public void setNodes(List<SysOrg> nodes) {
        this.nodes = nodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public String getIsRentOrg() {
        return isRentOrg;
    }

    public void setIsRentOrg(String isRentOrg) {
        this.isRentOrg = isRentOrg;
    }

    public String getIsTwoLevel() {
        return isTwoLevel;
    }

    public void setIsTwoLevel(String isTwoLevel) {
        this.isTwoLevel = isTwoLevel;
    }

    @Override
    public boolean equals(Object o) {
        //自反性
        if (this == o) return true;
        //任何对象不等于null，比较是否为同一类型
        if (!(o instanceof SysOrg)) return false;
        //强制类型转换
        SysOrg sysOrg = (SysOrg) o;
        //比较属性值
        return this.getUuid().equals(sysOrg.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUuid());
    }
}

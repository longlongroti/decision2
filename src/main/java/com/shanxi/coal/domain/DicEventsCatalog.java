package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DicEventsCatalog implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private List<DicEventsCatalog> children;
    private String uuid;
    private Date createdDate;
    private String createdBy;
    private String createdByOrg;
    private String modifiedBy;
    private Date modifiedDate;
    private Byte isDel;
    private Byte status;
    private String remark;
    private String catalogA;
    private String catalogB;
    private String catalogC;
    private String catalogCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DicEventsCatalog> getChildren() {
        return children;
    }

    public void setChildren(List<DicEventsCatalog> children) {
        this.children = children;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public String getCreatedByOrg() {
        return createdByOrg;
    }

    public void setCreatedByOrg(String createdByOrg) {
        this.createdByOrg = createdByOrg == null ? null : createdByOrg.trim();
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCatalogA() {
        return catalogA;
    }

    public void setCatalogA(String catalogA) {
        this.catalogA = catalogA == null ? null : catalogA.trim();
    }

    public String getCatalogB() {
        return catalogB;
    }

    public void setCatalogB(String catalogB) {
        this.catalogB = catalogB == null ? null : catalogB.trim();
    }

    public String getCatalogC() {
        return catalogC;
    }

    public void setCatalogC(String catalogC) {
        this.catalogC = catalogC == null ? null : catalogC.trim();
    }

    public String getCatalogCode() {
        return catalogCode;
    }

    public void setCatalogCode(String catalogCode) {
        this.catalogCode = catalogCode == null ? null : catalogCode.trim();
    }

}
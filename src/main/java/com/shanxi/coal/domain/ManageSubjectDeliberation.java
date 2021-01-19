package com.shanxi.coal.domain;

import java.io.Serializable;

public class ManageSubjectDeliberation implements Serializable {
    private String uuid;

    private String subjectId;

    private String deliberationPersonnel;

    private String deliberationResult;

    private String deliberationId;

    private static final long serialVersionUID = 1L;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getDeliberationPersonnel() {
        return deliberationPersonnel;
    }

    public void setDeliberationPersonnel(String deliberationPersonnel) {
        this.deliberationPersonnel = deliberationPersonnel == null ? null : deliberationPersonnel.trim();
    }

    public String getDeliberationResult() {
        return deliberationResult;
    }

    public void setDeliberationResult(String deliberationResult) {
        this.deliberationResult = deliberationResult == null ? null : deliberationResult.trim();
    }

    public String getDeliberationId() {
        return deliberationId;
    }

    public void setDeliberationId(String deliberationId) {
        this.deliberationId = deliberationId;
    }
}
package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.List;

public class SysDataRole extends CommonBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String pows;
    private String text;
    private List<SysDataRole> nodes;
    private List<SysDataRolePower> powers;


    public String getPows() {
        return pows;
    }

    public void setPows(String pows) {
        this.pows = pows;
    }

    public List<SysDataRole> getNodes() {
        return nodes;
    }

    public void setNodes(List<SysDataRole> nodes) {
        this.nodes = nodes;
    }

    public List<SysDataRolePower> getPowers() {
        return powers;
    }

    public void setPowers(List<SysDataRolePower> powers) {
        this.powers = powers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}

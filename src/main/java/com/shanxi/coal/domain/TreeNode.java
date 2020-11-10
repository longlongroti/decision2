package com.shanxi.coal.domain;

import java.io.Serializable;
import java.util.List;

public class TreeNode extends CommonBean implements Serializable {

    private String parent;
    private String text;
    private List<TreeNode> nodes;
    private TreeNodeState state;

    public TreeNodeState getState() {
        return state;
    }

    public void setState(TreeNodeState state) {
        this.state = state;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }
}

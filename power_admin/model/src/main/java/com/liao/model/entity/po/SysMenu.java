package com.liao.model.entity.po;

import com.liao.model.entity.base.BaseEntity;

import java.util.List;

public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long parentId;

    private String name;

    private Integer type;

    private String path;

    private String component;

    private String perms;

    private String icon;

    private Integer sortValue;

    private Integer status;

    // 下级列表
    private List<SysMenu> children;
    // 是否选中
    private boolean isSelect;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return "SysMenu{" +
                "parentId=" + parentId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", path='" + path + '\'' +
                ", component='" + component + '\'' +
                ", perms='" + perms + '\'' +
                ", icon='" + icon + '\'' +
                ", sortValue=" + sortValue +
                ", status=" + status +
                ", children=" + children +
                ", isSelect=" + isSelect +
                '}';
    }
}


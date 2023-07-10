package com.liao.model.entity.system;

import com.liao.model.entity.base.BaseEntity;

import java.util.Objects;


public class SysPost extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String postCode;

    private String name;

    private String description;

    private Integer status;

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SysPost sysPost = (SysPost) o;
        return Objects.equals(postCode, sysPost.postCode) && Objects.equals(name, sysPost.name) && Objects.equals(description, sysPost.description) && Objects.equals(status, sysPost.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), postCode, name, description, status);
    }

    @Override
    public String toString() {
        return "SysPost{" +
                "postCode='" + postCode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
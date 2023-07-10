package com.liao.model.entity.po;

import com.liao.model.entity.base.BaseEntity;

import java.util.List;
import java.util.Objects;

public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String headUrl;

    private Long deptId;

    private Long postId;

    private String description;

    private String openId;

    private Integer status;

    private List<SysRole> roleList;
    // 岗位
    private String postName;
    // 部门
    private String deptName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SysUser sysUser = (SysUser) o;
        return Objects.equals(username, sysUser.username) && Objects.equals(password, sysUser.password) && Objects.equals(name, sysUser.name) && Objects.equals(phone, sysUser.phone) && Objects.equals(headUrl, sysUser.headUrl) && Objects.equals(deptId, sysUser.deptId) && Objects.equals(postId, sysUser.postId) && Objects.equals(description, sysUser.description) && Objects.equals(openId, sysUser.openId) && Objects.equals(status, sysUser.status) && Objects.equals(roleList, sysUser.roleList) && Objects.equals(postName, sysUser.postName) && Objects.equals(deptName, sysUser.deptName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, name, phone, headUrl, deptId, postId, description, openId, status, roleList, postName, deptName);
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", deptId=" + deptId +
                ", postId=" + postId +
                ", description='" + description + '\'' +
                ", openId='" + openId + '\'' +
                ", status=" + status +
                ", roleList=" + roleList +
                ", postName='" + postName + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}


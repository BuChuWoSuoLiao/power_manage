package com.liao.model.entity.po;

import com.liao.model.entity.base.BaseEntity;

import java.util.Objects;

public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String roleName;

    private String roleCode;

    private String description;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SysRole sysRole = (SysRole) o;
        return Objects.equals(roleName, sysRole.roleName) && Objects.equals(roleCode, sysRole.roleCode) && Objects.equals(description, sysRole.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleName, roleCode, description);
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleName='" + roleName + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


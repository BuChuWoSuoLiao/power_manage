package com.liao.model.entity.po;

import com.liao.model.entity.base.BaseEntity;

import java.util.Objects;

public class SysUserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SysUserRole that = (SysUserRole) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleId, userId);
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}


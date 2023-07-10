package com.liao.model.entity.po;

import com.liao.model.entity.base.BaseEntity;

import java.util.Objects;

public class SysRoleMenu extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private Long menuId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		SysRoleMenu that = (SysRoleMenu) o;
		return Objects.equals(roleId, that.roleId) && Objects.equals(menuId, that.menuId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), roleId, menuId);
	}

	@Override
	public String toString() {
		return "SysRoleMenu{" +
				"roleId=" + roleId +
				", menuId=" + menuId +
				'}';
	}
}


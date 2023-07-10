package com.liao.model.entity.system;

import com.liao.model.entity.base.BaseEntity;

import java.util.List;

public class SysDept extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private Long parentId;

	private String treePath;

	private Integer sortValue;

	private String leader;

	private String phone;

	private Integer status;

	private List<SysDept> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public Integer getSortValue() {
		return sortValue;
	}

	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<SysDept> getChildren() {
		return children;
	}

	public void setChildren(List<SysDept> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "SysDept{" +
				"name='" + name + '\'' +
				", parentId=" + parentId +
				", treePath='" + treePath + '\'' +
				", sortValue=" + sortValue +
				", leader='" + leader + '\'' +
				", phone='" + phone + '\'' +
				", status=" + status +
				", children=" + children +
				'}';
	}
}
package com.liao.model.entity.base;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BaseEntity implements Serializable {
    private Long id;
    private Date createTime;    // 创建时间
    private Date updateTime;    // 修改时间
    private Integer isDeleted;  // 删除标志位
    private Map<String, Object> param = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(createTime, that.createTime) && Objects.equals(updateTime, that.updateTime) && Objects.equals(isDeleted, that.isDeleted) && Objects.equals(param, that.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, updateTime, isDeleted, param);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                ", param=" + param +
                '}';
    }
}

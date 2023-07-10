package com.liao.model.entity.query;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsPackage: com.liao.model.entity.query
 * @Author: Liao YunYang
 * @Description: 分配权限的query
 * @CreateTime: 2023-07-01  22:45
 * @Version: 1.0
 */
public class AssignRoleQuery implements Serializable {

    private static final long serialVersionUID = -2229412938267467314L;
    private Long userId;
    private List<Long> roleList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Long> roleList) {
        this.roleList = roleList;
    }
}

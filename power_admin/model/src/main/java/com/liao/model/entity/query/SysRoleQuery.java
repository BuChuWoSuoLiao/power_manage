package com.liao.model.entity.query;

import java.io.Serializable;

/**
 * @BelongsPackage: com.liao.model.entity.query
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-06-29  19:23
 * @Version: 1.0
 */
public class SysRoleQuery implements Serializable {
    private static final long serialVersionUID = 2776409645148730289L;

    // 角色名称
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

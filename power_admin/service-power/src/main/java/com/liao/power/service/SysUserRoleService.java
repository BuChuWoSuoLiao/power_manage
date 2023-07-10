package com.liao.power.service;

import com.liao.utils.ResponseResult;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @BelongsPackage: com.liao.power.service.impl
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-07-01  22:04
 * @Version: 1.0
 */
public interface SysUserRoleService {

    /**
     * 给用户分配权限
     *
     * @param userId  用户id
     * @param roleIds 角色id
     * @return
     */
    ResponseResult saveRoleByUserId(Long userId, String roleIds);

}

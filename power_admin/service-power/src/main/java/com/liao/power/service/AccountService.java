package com.liao.power.service;

import com.liao.utils.ResponseResult;

/**
 * @BelongsPackage: com.liao.power.service
 * @Author: Liao YunYang
 * @Description: 账户管理
 * @CreateTime: 2023-07-03  23:58
 * @Version: 1.0
 */
public interface AccountService {

    /**
     * 登录接口
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    ResponseResult login(String userName, String password);

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    ResponseResult getUserInfo(Long userId);

}

package com.liao.power.service;

import com.liao.model.entity.po.SysRole;
import com.liao.model.entity.po.SysUser;
import com.liao.model.entity.query.SysRoleQuery;
import com.liao.model.entity.query.SysUserQuery;
import com.liao.utils.ResponseResult;

/**
 * @BelongsPackage: com.liao.power.service
 * @Author: Liao YunYang
 * @Description: 用户业务层
 * @CreateTime: 2023-07-01  18:06
 * @Version: 1.0
 */
public interface SysUserService {

    /**
     * 获取所有用户
     *
     * @return
     */
    ResponseResult getSysUserList();


    /**
     * 根据id查询记录
     *
     * @param id 用户id
     * @return
     */
    ResponseResult getSysUserById(Long id);

    /**
     * 条件分页查询用户
     *
     * @param page      当前页
     * @param limit     每页显示记录数
     * @param userQuery 条件查询
     * @return
     */
    ResponseResult getUserPage(Integer page,
                               Integer limit,
                               SysUserQuery userQuery);

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return
     */
    ResponseResult saveUser(SysUser user);

    /**
     * 更改用户信息
     * id是必传参数
     *
     * @param user 角色对象
     * @return
     */
    ResponseResult changeUser(SysUser user);

    /**
     * 删除角色
     *
     * @param ids id集合
     * @return
     */
    ResponseResult removeUserByIds(String ids);



}

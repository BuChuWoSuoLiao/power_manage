package com.liao.power.service;

import com.liao.model.entity.po.SysRole;
import com.liao.model.entity.query.SysRoleQuery;
import com.liao.utils.ResponseResult;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @BelongsPackage: com.liao.power.service
 * @Author: Liao YunYang
 * @Description: 角色业务层
 * @CreateTime: 2023-06-28  22:59
 * @Version: 1.0
 */
public interface SysRoleService {

    /**
     * 获取所有角色
     *
     * @return 所有角色，集合
     */
    ResponseResult getSysRoleList();

    /**
     * 给角色分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单id集合
     * @return
     */
    ResponseResult doAssign(Long roleId, String menuIds);

    /**
     * 根据id查询记录
     *
     * @param id 角色id
     * @return
     */
    ResponseResult getSysRoleById(Integer id);

    /**
     * 条件分页查询角色
     *
     * @param page      当前页
     * @param limit     每页显示记录数
     * @param roleQuery 条件查询
     * @return
     */
    ResponseResult getRolePage(Integer page,
                               Integer limit,
                               SysRoleQuery roleQuery);

    /**
     * 添加role对象
     *
     * @param role 角色对象
     * @return
     */
    ResponseResult saveRole(SysRole role);

    /**
     * 更改角色信息
     * id是必传参数
     *
     * @param role 角色对象
     * @return
     */
    ResponseResult changeRole(SysRole role);

    /**
     * 删除角色
     *
     * @param ids id集合
     * @return
     */
    ResponseResult removeRoleByIds(String ids);

    /**
     * 根据用户id查询他所拥有的角色
     *
     * @param userId 用户id
     * @return
     */
    ResponseResult getRoleByUserId(Long userId);
}

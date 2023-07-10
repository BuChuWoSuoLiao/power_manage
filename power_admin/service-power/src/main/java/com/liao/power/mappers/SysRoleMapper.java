package com.liao.power.mappers;

import com.liao.model.entity.po.SysRole;
import com.liao.model.entity.query.SysRoleQuery;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsPackage: com.liao.admin.mappers
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-06-19  15:38
 * @Version: 1.0
 */
@Mapper()
public interface SysRoleMapper {

    /**
     * 查询所有记录,
     * 当 is_deleted == 0时 查询 不可用
     * 1时 可用
     * 2时 所有
     *
     * @return SysRole
     */
    List<SysRole> selectByAll(@Param("query") SysRoleQuery SysRoleQuery);

    /**
     * 根据id查询记录
     *
     * @param id id
     * @return
     */
    SysRole selectById(@Param("id") Integer id);

    /**
     * 添加角色
     *
     * @param sysRole 角色实体类
     * @return
     */
    void insertRole(@Param("role") SysRole sysRole);

    /**
     * 根据role对象更改数据库值
     *
     * @param role 角色对象
     * @return Integer
     */
    void updateRoleByRole(SysRole role);


    /**
     * 根据id删除角色
     *
     * @param list 角色id集合
     * @return
     */
    void deleteRoleByIds(@Param("list") List<Integer> list);

    /**
     * 根据用户id获取角色列表
     *
     * @param userId 用户id
     * @return
     */
    List<SysRole> selectRolesByUserId(@Param("id") Long userId);

    /**
     * 根据角色名称查询角色信息
     *
     * @param roleName 角色名称
     * @return
     */
    SysRole selectRoleByRoleName(@Param("roleName") String roleName);

}

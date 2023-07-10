package com.liao.power.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsPackage: com.liao.power.mappers
 * @Author: Liao YunYang
 * @Description: 角色和菜单持久层
 * @CreateTime: 2023-07-02  23:59
 * @Version: 1.0
 */
@Mapper
public interface SysRoleMenuMapper {

    /**
     * 为角色添加菜单
     *
     * @param roleId     角色id
     * @param menuIdList 菜单列表
     * @return
     */
    Integer insertUserRoleByUserIdAndRoleIds(@Param("roleId") Long roleId, @Param("menuIdList") List<Long> menuIdList);

    /**
     * 根据角色id删除所有菜单
     *
     * @param roleId 角色id
     * @return
     */
    Integer deleteRoleMenuAllByRoleId(@Param("roleId") Long roleId);
}

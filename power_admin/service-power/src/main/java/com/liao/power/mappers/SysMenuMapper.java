package com.liao.power.mappers;

import com.liao.model.entity.po.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsPackage: com.liao.power.mappers
 * @Author: Liao YunYang
 * @Description: 菜单管理持久层
 * @CreateTime: 2023-07-02  16:21
 * @Version: 1.0
 */
@Mapper
public interface SysMenuMapper {

    /**
     * 获取所有菜单列表
     *
     * @return
     */
    List<SysMenu> selectMenuAll(@Param("flag") boolean statusFlag);

    /**
     * 根据角色id获取所对应的菜单对象
     *
     * @param roleId 角色id
     * @return
     */
    List<SysMenu> selectMenusByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据id查询以该id的子节点
     *
     * @param id 当前菜单id
     * @return
     */
    List<SysMenu> selectMenuParentByMenuId(@Param("id") Long id);

    /**
     * 添加菜单
     *
     * @param menu 菜单对象
     * @return
     */
    Integer insertMenu(@Param("menu") SysMenu menu);

    /**
     * 修改菜单
     *
     * @param menu 菜单对象
     * @return
     */
    Integer updateMenuById(SysMenu menu);

    /**
     * 根据id列表删除菜单
     *
     * @param ids id列表
     * @return
     */
    Integer deleteMenuByIds(@Param("idList") List<Long> ids);

    /**
     * 根据用户id查询该用户的菜单列表
     *
     * @param userId 用户id
     * @return
     */
    List<SysMenu> selectMenusByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id查询该用户的按钮列表
     *
     * @param userId 用户id
     * @return
     */
    List<SysMenu> selectMenusPermsByUserId(@Param("userId") Long userId);

}

package com.liao.power.service;

import com.liao.model.entity.po.SysMenu;
import com.liao.model.entity.po.SysRole;
import com.liao.utils.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @BelongsPackage: com.liao.power.service
 * @Author: Liao YunYang
 * @Description: 菜单管理业务层
 * @CreateTime: 2023-07-02  16:22
 * @Version: 1.0
 */
@Service
public interface SysMenuService {

    /**
     * 获取菜单列表
     *
     * @return
     */
    ResponseResult getMenuList();

    /**
     * 获取所有菜单和角色分配了的菜单
     *
     * @return
     */
    ResponseResult getAssign(Long roleId);

    /**
     * 添加menu对象
     *
     * @param menu 菜单对象
     * @return
     */
    ResponseResult saveMenu(SysMenu menu);

    /**
     * 修改menu对象
     *
     * @param menu 菜单对象
     * @return
     */
    ResponseResult changeMenuById(SysMenu menu);

    /**
     * 根据id删除菜单对象
     *
     * @param ids id列表
     * @return
     */
    ResponseResult removeMenuById(String ids);

}

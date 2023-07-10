package com.liao.power.service.impl;

import com.liao.exception.SqlException.DeleteException;
import com.liao.exception.SqlException.UpdateException;
import com.liao.model.entity.enums.ResultCodeEnum;
import com.liao.model.entity.po.SysMenu;
import com.liao.power.mappers.SysMenuMapper;
import com.liao.power.service.SysMenuService;
import com.liao.power.utils.MenuHelperUtil;
import com.liao.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsPackage: com.liao.power.service.impl
 * @Author: Liao YunYang
 * @Description: 菜单管理业务层
 * @CreateTime: 2023-07-02  16:22
 * @Version: 1.0
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 获取菜单列表
     *
     * @return
     */
    @Override
    public ResponseResult getMenuList() {
        // 查询所有菜单数据
        List<SysMenu> sysMenus = sysMenuMapper.selectMenuAll(false);
        // 构建树形结构
        List<SysMenu> result = MenuHelperUtil.buildTree(sysMenus);

        return ResponseResult.ok().put("menuList", result);
    }

    /**
     * 获取所有菜单和角色分配了的菜单
     *
     * @return
     */
    @Override
    public ResponseResult getAssign(Long roleId) {
        // 查询所有菜单
        List<SysMenu> menuAllList = sysMenuMapper.selectMenuAll(true);

        // 构建树形菜单
        List<SysMenu> sysMenus = MenuHelperUtil.buildTree(menuAllList);

        // 根据角色id查询角色id对应的所有菜单对象
        List<SysMenu> roleMenuList = sysMenuMapper.selectMenusByRoleId(roleId);

        List<Long> collect = roleMenuList.stream()
                .map(list -> list.getId())
                .collect(Collectors.toList());

        return ResponseResult.ok().put("menuAllList", sysMenus).put("roleMenuList", collect);
    }

    /**
     * 添加菜单
     *
     * @param menu 菜单对象
     * @return
     */
    @Override
    public ResponseResult saveMenu(SysMenu menu) {
        try {
            sysMenuMapper.insertMenu(menu);
        } catch (Exception e) {
            throw new UpdateException(ResultCodeEnum.CODE_600.getMsg());
        }
        return ResponseResult.ok();
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单对象
     * @return
     */
    @Override
    public ResponseResult changeMenuById(SysMenu menu) {
        try {
            sysMenuMapper.updateMenuById(menu);
        } catch (Exception e) {
            throw new UpdateException(ResultCodeEnum.CODE_601.getMsg());
        }

        return ResponseResult.ok();
    }

    /**
     * 删除菜单
     *
     * @param ids id列表
     * @return
     */
    @Override
    public ResponseResult removeMenuById(String ids) {
        try {
            String[] split = ids.split(",");

            List<Long> collect = Arrays.stream(split)
                    .map(id -> Long.parseLong(id))
                    .collect(Collectors.toList());

            for (Long id : collect) {
                List<SysMenu> sysMenus = sysMenuMapper.selectMenuParentByMenuId(id);
                if (sysMenus.size() > 0) return ResponseResult.error(200, "该菜单还有子菜单未删除");
            }

            sysMenuMapper.deleteMenuByIds(collect);
        } catch (Exception e) {
            throw new DeleteException(ResultCodeEnum.CODE_602.getMsg());
        }

        return ResponseResult.ok();
    }
}

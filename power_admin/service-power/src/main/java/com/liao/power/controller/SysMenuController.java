package com.liao.power.controller;

import com.liao.model.entity.po.SysMenu;
import com.liao.power.service.SysMenuService;
import com.liao.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsPackage: com.liao.power.controller
 * @Author: Liao YunYang
 * @Description: 菜单管理控制层
 * @CreateTime: 2023-07-02  16:20
 * @Version: 1.0
 */
@Api(tags = "菜单管理接口")
@RestController
@RequestMapping("/admin/sys/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "获取菜单")
    @GetMapping("getMenuList")
    @PreAuthorize("hasAnyAuthority('bnt.sysMenu.list')")
    public ResponseResult getMenuList() {
        return sysMenuService.getMenuList();
    }

    @ApiOperation(value = "获取所有菜单和角色分配了的菜单")
    @GetMapping("getAssign/{roleId}")
    @PreAuthorize("hasAnyAuthority('bnt.sysMenu.list')")
    public ResponseResult getMenuAndRoleMenuList(@PathVariable("roleId") Long roleId) {
        return sysMenuService.getAssign(roleId);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("saveMenu")
    @PreAuthorize("hasAnyAuthority('bnt.sysMenu.add')")
    public ResponseResult saveMenu(@RequestBody SysMenu menu) {
        return sysMenuService.saveMenu(menu);
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("changeMenu")
    @PreAuthorize("hasAnyAuthority('bnt.sysMenu.update')")
    public ResponseResult changeMenuById(@RequestBody SysMenu menu) {

        return sysMenuService.changeMenuById(menu);
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{ids}")
    @PreAuthorize("hasAnyAuthority('bnt.sysMenu.remove')")
    public ResponseResult removeMenuByIds(@PathVariable("ids") String ids) {

        return sysMenuService.removeMenuById(ids);
    }

}

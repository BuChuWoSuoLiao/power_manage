package com.liao.power.controller;

import com.liao.model.entity.po.SysRole;
import com.liao.model.entity.query.SysRoleQuery;
import com.liao.power.service.SysRoleService;
import com.liao.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsPackage: com.liao.power.controller
 * @Author: Liao YunYang
 * @Description: 角色控制层
 * @CreateTime: 2023-06-28  22:58
 * @Version: 1.0
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/sys/role")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "查询所有角色记录")
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('bnt.sysRole.list')")
    public ResponseResult getAll() {
        return sysRoleService.getSysRoleList();
    }


    @ApiOperation(value = "条件分页查询角色")
    @GetMapping("/page/{page}/{limit}")
    @PreAuthorize("hasAnyAuthority('bnt.sysRole.list')")
    public ResponseResult getRolePage(@PathVariable("page") Integer page,
                                      @PathVariable("limit") Integer limit,
                                      SysRoleQuery roleQuery) {
        return sysRoleService.getRolePage(page, limit, roleQuery);
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/saveRole")
    @PreAuthorize("hasAnyAuthority('bnt.sysRole.add')")
    public ResponseResult saveRole(@RequestBody SysRole role) {
        return sysRoleService.saveRole(role);
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("/changeRole")
    @PreAuthorize("hasAnyAuthority('bnt.sysRole.update')")
    public ResponseResult changeRole(@RequestBody SysRole role) {
        return sysRoleService.changeRole(role);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/remove/{ids}")
    @PreAuthorize("hasAnyAuthority('bnt.sysRole.remove')")
    public ResponseResult removeRole(@PathVariable String ids) {
        return sysRoleService.removeRoleByIds(ids);
    }

    @ApiOperation(value = "根据id查询角色")
    @GetMapping("/getRoleById/{id}")
    @PreAuthorize("hasAnyAuthority('bnt.sysRole.list')")
    public ResponseResult getRoleById(@PathVariable Integer id) {
        return sysRoleService.getSysRoleById(id);
    }

    @ApiOperation(value = "获取用户角色信息")
    @GetMapping("/getRoleByUserId/{userId}")
    @PreAuthorize("hasAnyAuthority('bnt.sysRole.list')")
    public ResponseResult getRolesByUserId(@PathVariable Long userId) {
        return sysRoleService.getRoleByUserId(userId);
    }

    @ApiOperation(value = "为角色分配菜单")
    @PostMapping("doAssign/{roleId}/{menuIds}")
    @PreAuthorize("hasAnyAuthority('bnt.sysRole.assignMenu')")
    public ResponseResult doAssign(@PathVariable("roleId") Long roleId, @PathVariable("menuIds") String menuIds) {
        return sysRoleService.doAssign(roleId, menuIds);
    }
}

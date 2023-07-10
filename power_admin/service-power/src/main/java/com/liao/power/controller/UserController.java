package com.liao.power.controller;

import com.liao.model.entity.po.SysRole;
import com.liao.model.entity.po.SysUser;
import com.liao.model.entity.query.SysRoleQuery;
import com.liao.model.entity.query.SysUserQuery;
import com.liao.power.service.SysUserRoleService;
import com.liao.power.service.SysUserService;
import com.liao.utils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsPackage: com.liao.power.controller
 * @Author: Liao YunYang
 * @Description: 用户管理控制层
 * @CreateTime: 2023-07-01  18:03
 * @Version: 1.0
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/sys/user")
public class UserController {

    @Autowired
    private SysUserService userService;
    @Autowired
    private SysUserRoleService userRoleService;

    @ApiOperation(value = "查询所有用户记录")
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('bnt.sysUser.list')")
    public ResponseResult getAll() {
        return userService.getSysUserList();
    }

    @ApiOperation(value = "条件分页查询用户")
    @GetMapping("/page/{page}/{limit}")
    @PreAuthorize("hasAnyAuthority('bnt.sysUser.list')")
    public ResponseResult getUserPage(@PathVariable("page") Integer page,
                                      @PathVariable("limit") Integer limit,
                                      SysUserQuery userQuery) {
        return userService.getUserPage(page, limit, userQuery);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/saveUser")
    @PreAuthorize("hasAnyAuthority('bnt.sysUser.add')")
    public ResponseResult saveUser(@RequestBody SysUser user) {
        return userService.saveUser(user);
    }

    @ApiOperation(value = "修改用户")
    @PutMapping("/changeUser")
    @PreAuthorize("hasAnyAuthority('bnt.sysUser.update')")
    public ResponseResult changeUser(@RequestBody SysUser user) {
        return userService.changeUser(user);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{ids}")
    @PreAuthorize("hasAnyAuthority('bnt.sysUser.remove')")
    public ResponseResult removeUser(@PathVariable String ids) {
        return userService.removeUserByIds(ids);
    }

    @ApiOperation(value = "根据id查询用户")
    @GetMapping("/getUserById/{id}")
    @PreAuthorize("hasAnyAuthority('bnt.sysUser.list')")
    public ResponseResult getUserById(@PathVariable Long id) {
        return userService.getSysUserById(id);
    }

    @ApiOperation(value = "为用户分配角色")
    @PostMapping("/saveRoleByUserId/{userId}/{roleIds}")
    @PreAuthorize("hasAnyAuthority('bnt.sysUser.assignRole')")
    public ResponseResult assignRolesByUserIdAndRoleIds(@PathVariable("userId") Long userId
            , @PathVariable("roleIds") String roleIds) {
        return userRoleService.saveRoleByUserId(userId, roleIds);
    }
}

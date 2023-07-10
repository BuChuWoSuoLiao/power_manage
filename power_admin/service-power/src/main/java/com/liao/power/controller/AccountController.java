package com.liao.power.controller;

import com.alibaba.fastjson.JSON;
import com.liao.model.entity.query.SysUserQuery;
import com.liao.power.service.AccountService;
import com.liao.utils.JwtUtil;
import com.liao.utils.ResponseResult;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @BelongsPackage: com.liao.power.controller
 * @Author: Liao YunYang
 * @Description: 账号管理控制层
 * @CreateTime: 2023-06-30  16:04
 * @Version: 1.0
 */
@Api(tags = "账号管理接口")
@RestController
@RequestMapping("/admin")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 登录
     *
     * @param userQuery 用户信息
     * @return
     */
    @ApiOperation(value = "登录接口")
    @PostMapping("/account/login")
    public ResponseResult login(@RequestBody SysUserQuery userQuery) {
        return accountService.login(userQuery.getUserName(), userQuery.getPassword());
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/account/info")
    public ResponseResult info(HttpServletRequest request) {
        String token = request.getHeader("token");

        System.out.println(token);

        Long userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Long.valueOf(claims.getSubject());// 取出用户id
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        return accountService.getUserInfo(userId);
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation(value = "退出登录")
    @PostMapping("/account/logout")
    public ResponseResult logout() {
        return ResponseResult.ok();
    }

}

package com.liao.power.service.impl;

import com.liao.exception.UserException.PasswordWrongException;
import com.liao.model.entity.po.CustomLoginUser;
import com.liao.model.entity.po.SysMenu;
import com.liao.model.entity.po.SysUser;
import com.liao.model.entity.vo.SysUserVo;
import com.liao.model.entity.vo.RouterVo;
import com.liao.power.mappers.SysMenuMapper;
import com.liao.power.mappers.SysUserMapper;
import com.liao.power.service.AccountService;
import com.liao.power.utils.MenuHelperUtil;
import com.liao.utils.JwtUtil;
import com.liao.power.utils.RedisUtil;
import com.liao.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @BelongsPackage: com.liao.power.service.impl
 * @Author: Liao YunYang
 * @Description: 账户持久层
 * @CreateTime: 2023-07-03  23:58
 * @Version: 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Resource
    RedisUtil redisUtil;

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    @Override
    public ResponseResult login(String userName, String password) {
        // 1、使用ProviderManger 的 authenticate方法进行验证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, password);

        Authentication authenticate = null;

        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            throw new PasswordWrongException("用户名或密码错误！");
        }


        // 生成自己的jwt给前端
        /**
         * authenticate.getPrincipal() 从 authenticate 对象中拿出LoginUser信息
         * JwtUtil.createJWT(userId) 根据 userId 生成jwt令牌
         * map.put("token", jwt) 将jwt令牌以 key = token 存入 Result 发送给前端
         */
        CustomLoginUser loginUser = (CustomLoginUser) (authenticate.getPrincipal());
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        // 将用户相关所有信息放入redis
        redisUtil.setCacheObject("login:" + userId, loginUser);

        return ResponseResult.ok().put("token", jwt);
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public ResponseResult getUserInfo(Long userId) {

        // 根据用户id查询用户信息
        SysUser userInfo = sysUserMapper.selectUserById(userId);
        SysUserVo resultUserVo = new SysUserVo();
        resultUserVo.setName(userInfo.getName());
        resultUserVo.setHeadUrl(userInfo.getHeadUrl());

        List<SysMenu> sysMenuList = null;
        if (userId == 1) {
            sysMenuList = sysMenuMapper.selectMenuAll(true);
        } else {
            // 根据用户id查询该用户的菜单列表
            sysMenuList = sysMenuMapper.selectMenusByUserId(userId);
        }
        // 构建树形数据
        List<SysMenu> sysMenuTreeList = MenuHelperUtil.buildTree(sysMenuList);

        // 构建路由
        List<RouterVo> routerVoList = MenuHelperUtil.buildRouters(sysMenuTreeList);

        // 根据id查询权限列表
        List<SysMenu> sysMenus = sysMenuMapper.selectMenusPermsByUserId(userId);
        List<String> permsList = sysMenus.stream()
                .map(item -> item.getPerms())
                .collect(Collectors.toList());

        return ResponseResult.ok().put("routerVoList", routerVoList).put("permsList", permsList).put("userInfo", resultUserVo);
    }
}

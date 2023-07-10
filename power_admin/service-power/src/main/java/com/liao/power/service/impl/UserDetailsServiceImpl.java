package com.liao.power.service.impl;

import com.liao.model.entity.po.CustomLoginUser;
import com.liao.model.entity.po.SysMenu;
import com.liao.model.entity.po.SysUser;
import com.liao.power.mappers.SysMenuMapper;
import com.liao.power.mappers.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @BelongsPackage: com.liao.power.service.impl
 * @Author: Liao YunYang
 * @Description: 用户认证方法
 * @CreateTime: 2023-07-06  00:06
 * @Version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 根据用户名获取数据库中的系统用户
        SysUser sysUser = sysUserMapper.selectUserByUserName(username);
        if (Objects.isNull(sysUser)) {
            throw new RuntimeException("没有用户！");
        }

        //数据库中查询权限信息
        List<SysMenu> sysMenus = sysMenuMapper.selectMenusPermsByUserId(sysUser.getId());
        // 封装权限名称
        List<String> menus = sysMenus.stream()
                .map(item -> item.getPerms())
                .collect(Collectors.toList());

        //返回UserDetails
        return new CustomLoginUser(sysUser, menus);
    }
}

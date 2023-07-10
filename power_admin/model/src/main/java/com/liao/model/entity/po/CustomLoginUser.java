package com.liao.model.entity.po;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsPackage: com.liao.security.custom
 * @Author: Liao YunYang
 * @Description: 自定义用户类，替代UserDetails
 * @CreateTime: 2023-07-06  00:00
 * @Version: 1.0
 */
public class CustomLoginUser implements UserDetails {

    private SysUser sysUser;
    private List<String> permissions;   //权限信息
    @JSONField(serialize = false)   //此成员变量不会序列化到redis中
    private List<SimpleGrantedAuthority> authorities;

    public CustomLoginUser(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    public CustomLoginUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public SysUser getUser() {
        return sysUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //如果集合不为空直接返回权限对象
        if (authorities != null) {
            return authorities;
        }

        //重新封装权限信息
        //函数式编程
        authorities = permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
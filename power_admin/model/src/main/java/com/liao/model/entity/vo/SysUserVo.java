package com.liao.model.entity.vo;

import com.liao.model.entity.po.SysRole;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @BelongsPackage: com.liao.model.entity.vo
 * @Author: Liao YunYang
 * @Description: 返回用户信息
 * @CreateTime: 2023-07-05  21:52
 * @Version: 1.0
 */
public class SysUserVo implements Serializable {
    private static final long serialVersionUID = 2813866949153819907L;

    private String name;

    private String headUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserVo sysUserVo = (SysUserVo) o;
        return Objects.equals(name, sysUserVo.name) && Objects.equals(headUrl, sysUserVo.headUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, headUrl);
    }

    @Override
    public String toString() {
        return "SysUserVo{" +
                "name='" + name + '\'' +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }
}

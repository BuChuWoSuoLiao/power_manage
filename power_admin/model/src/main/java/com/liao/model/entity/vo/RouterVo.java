package com.liao.model.entity.vo;


import java.util.List;
import java.util.Objects;

/**
 * 路由配置信息
 */
public class RouterVo {
    /**
     * 路由名字
     */
    // private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private MetaVo meta;

    /**
     * 子路由
     */
    private List<RouterVo> children;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Boolean getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(Boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public MetaVo getMeta() {
        return meta;
    }

    public void setMeta(MetaVo meta) {
        this.meta = meta;
    }

    public List<RouterVo> getChildren() {
        return children;
    }

    public void setChildren(List<RouterVo> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouterVo routerVo = (RouterVo) o;
        return hidden == routerVo.hidden && Objects.equals(path, routerVo.path) && Objects.equals(component, routerVo.component) && Objects.equals(alwaysShow, routerVo.alwaysShow) && Objects.equals(meta, routerVo.meta) && Objects.equals(children, routerVo.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, hidden, component, alwaysShow, meta, children);
    }

    @Override
    public String toString() {
        return "RouterVo{" +
                "path='" + path + '\'' +
                ", hidden=" + hidden +
                ", component='" + component + '\'' +
                ", alwaysShow=" + alwaysShow +
                ", meta=" + meta +
                ", children=" + children +
                '}';
    }
}

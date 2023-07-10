package com.liao.model.entity.vo;


import java.io.Serializable;
import java.util.Objects;

/**
 * 路由显示信息
 */
public class MetaVo implements Serializable {
    private static final long serialVersionUID = -3027088741802039192L;
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    public MetaVo() {
    }

    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaVo metaVo = (MetaVo) o;
        return Objects.equals(title, metaVo.title) && Objects.equals(icon, metaVo.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, icon);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}


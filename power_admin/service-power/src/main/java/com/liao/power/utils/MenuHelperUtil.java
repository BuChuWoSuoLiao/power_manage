package com.liao.power.utils;

import com.liao.model.entity.po.SysMenu;
import com.liao.model.entity.vo.MetaVo;
import com.liao.model.entity.vo.RouterVo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsPackage: com.liao.power.utils
 * @Author: Liao YunYang
 * @Description: 菜单列表工具类
 * @CreateTime: 2023-07-02  20:35
 * @Version: 1.0
 */
public class MenuHelperUtil {

    /**
     * 使用递归方法建菜单
     *
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        sysMenu.setChildren(new ArrayList<SysMenu>());

        for (SysMenu it : treeNodes) {
            if (sysMenu.getId().longValue() == it.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return sysMenu;
    }

    /**
     * 根据菜单构建路由
     *
     * @param menus
     * @return
     */
    public static List<RouterVo> buildRouters(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden(false);
            router.setAlwaysShow(false);
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            List<SysMenu> children = menu.getChildren();
            // 如果当前是菜单，需将按钮对应的路由加载出来，如：“角色授权”按钮对应的路由在“系统管理”下面
            if (menu.getType().intValue() == 1) {
                List<SysMenu> hiddenMenuList = children.stream()
                        .filter(item -> !StringUtils.isEmpty(item.getComponent()))
                        .collect(Collectors.toList());
                for (SysMenu hiddenMenu : hiddenMenuList) {
                    RouterVo hiddenRouter = new RouterVo();
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(getRouterPath(hiddenMenu));
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVo(hiddenMenu.getName(), hiddenMenu.getIcon()));
                    routers.add(hiddenRouter);
                }
            } else {
                if (!CollectionUtils.isEmpty(children)) {
                    if (children.size() > 0) {
                        router.setAlwaysShow(true);
                    }
                    router.setChildren(buildRouters(children));
                }
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public static String getRouterPath(SysMenu menu) {
        String routerPath = "/" + menu.getPath();
        if (menu.getParentId().intValue() != 0) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }
}

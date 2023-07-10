package com.liao.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO web工具类，可直接向前端返回json数据
 * @BelongsProject: spring-security-demo
 * @BelongsPackage: com.liao.demo2.utils
 * @Author: LiaoYunYang
 * @CreateTime: 2023-03-08  00:02
 * @Version: 1.0
 */
public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
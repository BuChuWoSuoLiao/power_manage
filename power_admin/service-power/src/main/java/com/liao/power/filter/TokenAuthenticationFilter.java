package com.liao.power.filter;

import com.liao.model.entity.po.CustomLoginUser;
import com.liao.utils.JwtUtil;
import com.liao.power.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


/**
 * @BelongsPackage: com.liao.security.filter
 * @Author: Liao YunYang
 * @Description: Token令牌过滤器
 * @CreateTime: 2023-07-06  00:48
 * @Version: 1.0
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtil redisUtil;

    /**
     * 拦截器，拦截所有请求是否有token
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        logger.info("uri:" + request.getRequestURI());

        // 获取token,如果没有token直接放行
        String token = request.getHeader("token");
        // 没有token，放行
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token
        Long userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
             userId = Long.valueOf(claims.getSubject());// 取出用户id
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }

        // 再redis中查找用户信息 todo 在redis中取出用户信息
        String userKey = "login:" + userId;
        CustomLoginUser loginUser = redisUtil.getCacheObject(userKey);

        System.err.println(loginUser);

        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }

        // 存入SecurityContextHolder
        // TODO 获取权限信息封装到Authentication
        UsernamePasswordAuthenticationToken AuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(AuthenticationToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}

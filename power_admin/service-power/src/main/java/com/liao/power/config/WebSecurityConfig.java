package com.liao.power.config;

import com.liao.power.filter.TokenAuthenticationFilter;
import com.liao.power.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 允许使用权限注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Autowired
    public void setJwtAuthenticationTokenFilter(TokenAuthenticationFilter tokenAuthenticationFilter) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    /**
     * 向容器中注入密码的加密方式
     *
     * @return 返回密码加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 向容器中注入authenticationManagerBean完成认证
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //关联UserDetailsService对象
        auth.userDetailsService(userDetailsService);
    }

    // 配置security的类
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭csrf
                .csrf().disable()
                // 不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/admin/account/login","/swagger-ui.html").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 添加token过滤器
        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // 允许前端跨域
        http.cors();
    }
}

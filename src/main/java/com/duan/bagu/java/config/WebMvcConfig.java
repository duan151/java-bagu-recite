package com.duan.bagu.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Web 配置：注册登录拦截器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // 需要登录才能访问的路径
                .addPathPatterns("/", "/index", "/api/**")
                // 登录页、登录接口、错误页放行
                .excludePathPatterns("/login", "/login/**", "/error");
    }
}

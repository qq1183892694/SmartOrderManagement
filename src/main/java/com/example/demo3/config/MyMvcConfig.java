package com.example.demo3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("main").setViewName("dashboard");
        //重定向
    }
//配置登录拦截器,注意css只有一层
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new com.example.demo3.config.LoginHandlerinterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/LoginController/login","/order/add","/main","/css/*","/js/**","/img/**"); //配置登录拦截器,注意css只有一层
    }
}

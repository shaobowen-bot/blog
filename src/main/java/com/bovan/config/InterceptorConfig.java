package com.bovan.config;

import com.bovan.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration  //定义此类为配置文件（即相当于之前的xml配置文件）
public class InterceptorConfig implements WebMvcConfigurer {


    //mvc:interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //要拦截user下的所有请求,必须用户登录才可以访问，
        //但是这样拦截的路径中有一些是不需要用户登录也可访问的
        String[] addPathPatterns = {
                "/**"
        };
        //要排除的路径，排除的路径说明不需要用户登录也可访问
        String[] excludePathPatterns = {
                "/login", "/register", "/error"
        };
        //mvc:interceptor bean class=""
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}

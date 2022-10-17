package com.bovan.config;

/**
 * @BelongsProject: blog_boot
 * @BelongsPackage: com.bovan.config
 * @Author: bovan
 * @Date: 2022/10/14 10:54
 * @Description:
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","DELETE","OPTIONS","PUT")
                .allowCredentials(false)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}

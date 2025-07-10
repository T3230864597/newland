package com.oa4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CrosConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        //允许所有请求
        registry
                .addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("POST" , "GET");
    }
}

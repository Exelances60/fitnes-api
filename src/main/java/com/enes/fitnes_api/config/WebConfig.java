package com.enes.fitnes_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://javaui.vercel.app","https://javaui.vercel.app/")
                .allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*")
                .allowedOrigins("*");
    }
}
package com.IITB.chat.Config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfigWebSocket implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET","PUT","POST","DELETE")
                .allowedHeaders("Authorization" , "Content-Type")
                .exposedHeaders("Authorization" , "Content-Disposition")
                .allowCredentials(true)
                .maxAge(3600);
    }
}

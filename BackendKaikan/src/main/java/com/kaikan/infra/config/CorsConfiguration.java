package com.kaikan.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4400","http://localhost:4200","http://10.69.1.61:4400/","http://10.69.1.61:4200/","https://6c13-190-117-52-220.ngrok-free.app",  "http://10.69.1.61:4500/",
                        "http://10.69.1.61:4600/","http://10.69.1.61:8083/")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("*")  // Permite cabeceras personalizadas
                .allowCredentials(true);
    }
}

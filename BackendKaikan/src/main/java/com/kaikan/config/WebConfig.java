package com.kaikan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permite solicitudes CORS en todas las rutas
                .allowedOrigins("*")  // Permite solicitudes desde tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Los métodos permitidos
                .allowedHeaders("*");  // Permite cualquier cabecera
    }
}
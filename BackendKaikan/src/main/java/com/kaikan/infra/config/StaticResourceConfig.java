package com.kaikan.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Redirigir las rutas de recursos estáticos a la ruta "/public/**"
        registry.addResourceHandler("/public/**")
                .addResourceLocations("classpath:/static/"); // Ubicación de los recursos estáticos dentro de la carpeta "static"
    }

}

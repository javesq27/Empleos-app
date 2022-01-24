package com.proyecto.proyecto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.directorio-raiz}")
    private String directorioRaiz;

    public void addResourceHandlers(ResourceHandlerRegistry registry) { 
       
        registry.addResourceHandler("/logos/**").addResourceLocations("file:///" + directorioRaiz + "/empleos/img-vacantes/");
        registry.addResourceHandler("/cv/**").addResourceLocations("file:///" + directorioRaiz +"/empleos/cv-solicitudes/");

    }
    
}
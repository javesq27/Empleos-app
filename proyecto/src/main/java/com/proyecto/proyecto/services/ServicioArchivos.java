package com.proyecto.proyecto.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;


import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicioArchivos {

    @Value("${app.upload.directorio-raiz}")
    private String directorioRaiz;

    public String guardarArchivo(MultipartFile multiPart, String ruta) {
        
        String nombreOriginal = multiPart.getOriginalFilename();
        try {
            File imageFile = new File(directorioRaiz + ruta + nombreOriginal);
            multiPart.transferTo(imageFile);

            return nombreOriginal;
        } catch (IOException e) {
            return null;
        }
    }
    
}

package com.proyecto.proyecto;

import java.io.File;
import java.io.IOException;


import org.springframework.web.multipart.MultipartFile;

public class Utileria {
    
    
    public static String guardarArchivo(MultipartFile multiPart, String ruta) {
        
        String nombreOriginal = multiPart.getOriginalFilename();
        nombreOriginal.replace(" ", "-");
        try {
            File imageFile = new File(ruta + nombreOriginal);
            multiPart.transferTo(imageFile);

            return nombreOriginal;
        } catch (IOException e) {
            return null;
        }
    }


}

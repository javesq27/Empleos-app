package com.proyecto.proyecto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
    
    /*
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
    */
    public static boolean saveToFile(String path, String data)
    {
        try
        {
            FileOutputStream fileStream = new FileOutputStream(new File(path));
            OutputStreamWriter output = new OutputStreamWriter(fileStream);
            output.write(data);
            output.close();
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

}

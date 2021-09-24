package com.proyecto.proyecto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControladorHome {

    @GetMapping("/")
    public String mostrarHome(Model model) {
          
        return "home";
    }


    
}

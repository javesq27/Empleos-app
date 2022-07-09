package com.proyecto.proyecto.controllers;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.proyecto.proyecto.entities.Usuario;
import com.proyecto.proyecto.services.ServicioMails;
import com.proyecto.proyecto.services.ServicioUsuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    @Autowired
    private ServicioMails servicioMails;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Usuario> lista = servicioUsuarios.buscarTodos();
        model.addAttribute("usuarios", lista);
        return "usuarios/listUsuarios";

    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        try {
            servicioUsuarios.eliminar(idUsuario);
            attributes.addFlashAttribute("msg", "El usuario ha sido eliminado");

        }catch(Exception ex) {
            attributes.addFlashAttribute("msg", "No es posible eliminar el usuario");
        }
        
        return "redirect:/usuarios/index";
    }

    @GetMapping("/unlock/{id}")
	public String activar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        servicioUsuarios.activar(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue activado y ahora tiene acceso al sistema.");
        return "redirect:/usuarios/index";
	}

    @GetMapping("/lock/{id}")
	public String bloquear(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        servicioUsuarios.bloquear(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario fue bloqueado y no tendra acceso al sistema.");
        return "redirect:/usuarios/index";
	}

    @GetMapping("/sendEmail")
    public void email(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("alexisa.artopoulos@comunidad.ub.edu.ar");
        simpleMailMessage.setSubject("Solicitud en Compuempleos");
        simpleMailMessage.setText("Su solicitud en Compuempleos ha sido revisada");
        simpleMailMessage.setSentDate(Date.from(Instant.now()));
        simpleMailMessage.setFrom("conteros24@gmail.com");
        servicioMails.sendEmailSimple(simpleMailMessage);
    }

}


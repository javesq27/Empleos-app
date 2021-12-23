package com.proyecto.proyecto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Usuario> lista = servicioUsuarios.buscarTodos();
        model.addAttribute("usuarios", lista);
        return "usuarios/listUsuarios";

    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
        servicioUsuarios.eliminar(idUsuario);
        attributes.addFlashAttribute("msg", "El usuario ha sido eliminado");
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

}


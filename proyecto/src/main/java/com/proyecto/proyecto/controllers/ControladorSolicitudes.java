package com.proyecto.proyecto.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.proyecto.proyecto.entities.Solicitud;
import com.proyecto.proyecto.entities.Usuario;
import com.proyecto.proyecto.entities.Vacante;
import com.proyecto.proyecto.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/solicitudes")
public class ControladorSolicitudes {

    @Autowired
    private ISolicitudes servicioSolicitudes;

    @Autowired
    private IVacantes servicioVacantes;

    @Autowired
    private IUsuarios servicioUsuarios;

    @Autowired
    private ServicioMails servicioMails;

    @Autowired
    private ServicioArchivos servicioArchivos;


    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Solicitud> lista = servicioSolicitudes.buscarTodas();
        model.addAttribute("solicitudes", lista);

        return "solicitudes/listSolicitudes";

    } 

    @GetMapping("/create/{id}")
    public String crear(Solicitud solicitud, @PathVariable("id") int idVacante, Model model) {
        Vacante vacante = servicioVacantes.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);

        return "solicitudes/formSolicitud";
    }

    @PostMapping("/save")
    public String guardar(Solicitud solicitud, Model model, HttpSession session, RedirectAttributes attributes, @RequestParam("archivoCV") MultipartFile multiPart, Authentication authentication) {
        String username = authentication.getName();

        if(!multiPart.isEmpty()) {
            String nombreArchivo = servicioArchivos.guardarArchivo(multiPart, "/empleos/cv-solicitudes/");

            if(nombreArchivo != null) {
                solicitud.setArchivo(nombreArchivo);
            }

        }

        Usuario usuario = servicioUsuarios.buscarPorUsername(username);
        solicitud.setUsuario(usuario);
        solicitud.setFecha(new Date());

        servicioSolicitudes.guardar(solicitud);
        attributes.addFlashAttribute("msg", "Solicitud enviada");

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idSolicitud, RedirectAttributes attributes) {
        servicioSolicitudes.eliminar(idSolicitud);
        attributes.addFlashAttribute("msg", "La solicitud ha sido eliminada");

        return "redirect:/solicitudes/index";
    }

    @GetMapping("/revisar/{id}")
    public void revisar(@PathVariable("id") int idSolicitud){
        servicioSolicitudes.revisar(idSolicitud);
    }

    @InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}



    
}

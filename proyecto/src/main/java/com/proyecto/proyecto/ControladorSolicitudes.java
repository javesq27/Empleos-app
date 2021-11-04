package com.proyecto.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ControladorSolicitudes {

    @Autowired
    private ServicioSolicitudes servicioSolicitudes;

    @Autowired
    private ServicioVacantes servicioVacantes;

    @Autowired
    private ServicioUsuarios servicioUsuarios;


    @GetMapping("/index")
    public String mostrarInicio(Model model) {

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
    public String guardar(Solicitud solicitud, BindingResult result, Model model, HttpSession session, RedirectAttributes attributes, @RequestParam("archivoCV") MultipartFile multiPart, Authentication authentication) {

        String username = authentication.getName();

        if(result.hasErrors()) {
            System.out.println("Ocurrio un error");
            return "solicitudes/formSolicitud";
        }
        if (!multiPart.isEmpty()) {
            String ruta = "../empleos/cv-solicitudes/";
            String nombreArchivo = Utileria.guardarArchivo(multiPart, ruta);
            if (nombreArchivo != null){
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


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

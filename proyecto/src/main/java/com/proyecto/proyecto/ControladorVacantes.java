package com.proyecto.proyecto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vacantes")
public class ControladorVacantes {

    @Autowired
    private IVacantes servicioVacantes;

    @Autowired
    private ICategorias servicioCategorias;


    @GetMapping("/index")
    public String mostrarVacantes(Model model) {
        List<Vacante> lista =  servicioVacantes.buscarTodas();
        model.addAttribute("vacantes", lista);
        return "vacantes/listaVacantes";
    }

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model) {
        
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
        
        if (!multiPart.isEmpty()) { 
            String nombreImagen = Utileria.guardarArchivo(multiPart, "empleos/img-vacantes");
            
            if (nombreImagen != null){ 
                
                vacante.setImagen(nombreImagen);
            }
        }

        servicioVacantes.guardar(vacante);
        attributes.addFlashAttribute("msj", "La vacante ha sido guardada!!");
        
        return "redirect:/vacantes/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idVacante, RedirectAttributes attributes) {
        
        servicioVacantes.eliminar(idVacante);
        attributes.addFlashAttribute("msj", "La vacante ha sido eliminada!!");
        return "redirect:/vacantes/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = servicioVacantes.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        
        return "vacantes/formVacante";
    }

    @ModelAttribute
    public void setGenericos(Model model) {

        model.addAttribute("categorias", servicioCategorias.buscarTodas());
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); 
    }
    
}

package com.proyecto.proyecto.controllers;

import java.util.List;

import com.proyecto.proyecto.entities.Categoria;
import com.proyecto.proyecto.services.ICategorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
public class ControladorCategorias {

    @Autowired
    private ICategorias servicioCategorias;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Categoria> lista =  servicioCategorias.buscarTodas();
        model.addAttribute("categorias", lista);
        return "categorias/listCategorias";
    }


    @GetMapping("/create")
    public String crear(Categoria categoria, Model model) {
        
        return "categorias/formCategoria";
    }


    @PostMapping("/save")
    public String guardar(Categoria categoria, RedirectAttributes attributes) {
        
        servicioCategorias.guardar(categoria);
        attributes.addFlashAttribute("msj", "La categoria ha sido guardada!!");
        
        return "redirect:/categorias/index";
    }


    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {

        try {
            servicioCategorias.eliminar(idCategoria);
            attributes.addFlashAttribute("msg", "La categoria ha sido eliminada");

        }catch(Exception ex) {
            attributes.addFlashAttribute("msg", "No es posible eliminar la categoria");
        }
        
        return "redirect:/categorias/index";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idCategoria, Model model) {
        Categoria categoria = servicioCategorias.buscarPorId(idCategoria);
        model.addAttribute("categoria", categoria);

        return "categorias/formCategoria";
    }
}

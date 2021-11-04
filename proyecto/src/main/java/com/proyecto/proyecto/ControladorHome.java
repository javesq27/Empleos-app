package com.proyecto.proyecto;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ControladorHome {

    @Autowired
    private ICategorias servicioCategorias;

    @Autowired
    private IVacantes servicioVacantes;

    @Autowired
    private IUsuarios servicioUsuarios;

    @GetMapping("/")
    public String mostrarHome(Model model) {
          
        return "home";
    }

    @GetMapping("/index")
    public String mostrarIndex(Authentication auth, HttpSession session) {
        String username = auth.getName();

        if(session.getAttribute("usuario") == null) {
            Usuario usuario = servicioUsuarios.buscarPorUsername(username);
            servicioUsuarios.setContraseña(usuario, null);
            session.setAttribute("usuario", usuario);
        }
       
        return "redirect:/";
    }


    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {

        usuario.setEstatus(CodigoEstadoUsuario.HABILITADO);
		usuario.setFechaRegistro(new Date()); 
		
		Perfil perfil = new Perfil();
		perfil.setId(CodigoPerfilUsuario.USUARIO);
		usuario.agregar(perfil);
        
        servicioUsuarios.guardar(usuario);
        attributes.addFlashAttribute("msg", "El usuario se ha guardado");
        return "redirect:/usuarios/index";
    }

    @ModelAttribute
    public void setGenericos(Model model) {
    
        model.addAttribute("vacantes", servicioVacantes.buscarDestacadas());
        model.addAttribute("categorias", servicioCategorias.buscarTodas());
    }



    
}

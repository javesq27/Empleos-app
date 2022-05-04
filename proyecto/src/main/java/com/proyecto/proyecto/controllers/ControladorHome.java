package com.proyecto.proyecto.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.proyecto.proyecto.entities.Perfil;
import com.proyecto.proyecto.entities.Usuario;
import com.proyecto.proyecto.entities.Vacante;
import com.proyecto.proyecto.services.ICategorias;
import com.proyecto.proyecto.services.IUsuarios;
import com.proyecto.proyecto.services.IVacantes;
import com.proyecto.proyecto.utils.CodigoEstadoUsuario;
import com.proyecto.proyecto.utils.CodigoPerfilUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String mostrarHome(Model model) {
          
        return "home";
    }

    @GetMapping("/index")
    public String mostrarIndex(Authentication auth, HttpSession session) {
        String username = auth.getName();

    
        if(session.getAttribute("usuario") == null) {
            Usuario usuario = servicioUsuarios.buscarPorUsername(username);
            servicioUsuarios.setContrasenia(usuario, null);
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

        String pwdPlano = usuario.getPassword();
        String pwdEncriptado = passwordEncoder.encode(pwdPlano);
        usuario.setPassword(pwdEncriptado);

        usuario.setEstatus(CodigoEstadoUsuario.HABILITADO);
		usuario.setFechaRegistro(new Date()); 
		
		Perfil perfil = new Perfil();
        perfil.setId(CodigoPerfilUsuario.USUARIO);
		usuario.agregar(perfil);
        
        servicioUsuarios.guardar(usuario);
        attributes.addFlashAttribute("msg", "El usuario se ha guardado");
        return "redirect:/usuarios/index";
    }

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {
        
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Vacante> example = Example.of(vacante, matcher);
        List<Vacante> lista = servicioVacantes.buscarByExample(example);
        model.addAttribute("vacantes", lista);
        
        return "home";
    } 

    @GetMapping("/login")
    public String mostrarLogin() {
        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null); 
        return "redirect:/";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    } 

    @ModelAttribute
    public void setGenericos(Model model) {
    
        Vacante vacanteSearch = new Vacante();
        vacanteSearch.reset();            
        model.addAttribute("vacantes", servicioVacantes.buscarDestacadas());
        model.addAttribute("categorias", servicioCategorias.buscarTodas());
        model.addAttribute("search", vacanteSearch);
    }



    
}

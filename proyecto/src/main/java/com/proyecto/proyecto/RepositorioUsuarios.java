package com.proyecto.proyecto;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositorioUsuarios extends JpaRepository<Usuario, Integer>{

    Usuario findByUsername(String username);
    
}

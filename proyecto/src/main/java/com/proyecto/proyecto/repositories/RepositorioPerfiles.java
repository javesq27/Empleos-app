package com.proyecto.proyecto.repositories;

import com.proyecto.proyecto.entities.Perfil;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioPerfiles extends JpaRepository<Perfil, Integer> {
    
}

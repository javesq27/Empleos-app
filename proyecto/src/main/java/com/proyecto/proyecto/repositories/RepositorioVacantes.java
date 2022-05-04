package com.proyecto.proyecto.repositories;

import java.util.List;

import com.proyecto.proyecto.entities.Vacante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioVacantes extends JpaRepository<Vacante, Integer> {
   
    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
}

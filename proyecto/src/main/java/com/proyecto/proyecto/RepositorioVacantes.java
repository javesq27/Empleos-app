package com.proyecto.proyecto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioVacantes extends JpaRepository<Vacante, Integer> {
   
    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
}

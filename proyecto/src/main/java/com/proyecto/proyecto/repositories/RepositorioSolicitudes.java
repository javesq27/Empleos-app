package com.proyecto.proyecto.repositories;

import com.proyecto.proyecto.entities.Solicitud;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioSolicitudes extends JpaRepository<Solicitud, Integer> {

}

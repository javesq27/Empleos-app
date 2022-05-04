package com.proyecto.proyecto.services;

import java.util.List;

import com.proyecto.proyecto.entities.Vacante;

import org.springframework.data.domain.Example;

public interface IVacantes {
       
    void guardar(Vacante vacante);
    void eliminar(Integer idVacante);
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer idVacante);
    List<Vacante> buscarDestacadas();
    List<Vacante> buscarByExample(Example<Vacante> example);
}
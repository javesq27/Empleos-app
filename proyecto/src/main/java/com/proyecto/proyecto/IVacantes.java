package com.proyecto.proyecto;

import java.util.List;

import org.springframework.data.domain.Example;

public interface IVacantes {
       
    void guardar(Vacante vacante);
    void eliminar(Integer idVacante);
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer idVacante);
    List<Vacante> buscarDestacadas();
    List<Vacante> buscarByExample(Example<Vacante> example);
}
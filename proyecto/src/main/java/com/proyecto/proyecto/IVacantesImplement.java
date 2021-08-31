package com.proyecto.proyecto;

import java.util.List;

public interface IVacantesImplement {
       
    void guardar(Vacante vacante);
    void eliminar(Integer idVacante);
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer idVacante);
}
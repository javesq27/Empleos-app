package com.proyecto.proyecto;

import java.util.List;

public interface IVacantes {
       
    void guardar(Vacante vacante);
    void eliminar(Integer idVacante);
    List<Vacante> buscarTodas();
    Vacante buscarPorId(Integer idVacante);
    List<Vacante> buscarDestacadas();
}
package com.proyecto.proyecto;

import java.util.List;
import java.util.Optional;

public interface ICategorias {
    
    void guardar(Categoria categoria);
    void eliminar(Integer idCategoria);
    List<Categoria> buscarTodas();
    Optional<Categoria> buscarPorId(Integer idCategoria);
}

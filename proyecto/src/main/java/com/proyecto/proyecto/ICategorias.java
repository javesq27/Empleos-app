package com.proyecto.proyecto;

import java.util.List;


public interface ICategorias {
    
    void guardar(Categoria categoria);
    void eliminar(Integer idCategoria);
    List<Categoria> buscarTodas();
    Categoria buscarPorId(Integer idCategoria);
}

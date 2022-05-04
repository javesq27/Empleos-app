package com.proyecto.proyecto.services;

import java.util.List;

import com.proyecto.proyecto.entities.Categoria;


public interface ICategorias {
    
    void guardar(Categoria categoria);
    void eliminar(Integer idCategoria);
    List<Categoria> buscarTodas();
    Categoria buscarPorId(Integer idCategoria);
}

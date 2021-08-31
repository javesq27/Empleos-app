package com.proyecto.proyecto;

import java.util.List;

public interface ICategoriasImplement {
    
    void guardar(Categoria categoria);
    void eliminar(Integer idCategoria);
    List<Categoria> buscarTodas();
    int buscarPorId(Integer idCategoria);
}

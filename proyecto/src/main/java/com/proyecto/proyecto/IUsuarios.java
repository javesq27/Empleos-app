package com.proyecto.proyecto;

import java.util.List;

public interface IUsuarios {
    void guardar(Usuario usuario);
    void eliminar(Integer idUsuario);
    List<Usuario> buscarTodos();
    Usuario buscarPorId(Integer idUsuario);
    Usuario buscarPorUsername(String username);
    
}

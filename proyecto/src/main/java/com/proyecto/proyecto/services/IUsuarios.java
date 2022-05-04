package com.proyecto.proyecto.services;

import java.util.List;

import com.proyecto.proyecto.entities.Usuario;

public interface IUsuarios {
    void guardar(Usuario usuario);
    void eliminar(Integer idUsuario);
    List<Usuario> buscarTodos();
    Usuario buscarPorId(Integer idUsuario);
    Usuario buscarPorUsername(String username);
    void setContrasenia(Usuario usuario,String contraseña);
    int bloquear(int idUsuario);
	int activar(int idUsuario);
    
}

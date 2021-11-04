package com.proyecto.proyecto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ServicioUsuarios implements IUsuarios {


    private RepositorioUsuarios repositorioUsuarios;

    @Override
    public void guardar(Usuario usuario) {
        repositorioUsuarios.save(usuario);
        
    }

    @Override
    public void eliminar(Integer idUsuario) {
        repositorioUsuarios.deleteById(idUsuario);
        
    }

    @Override
    public List<Usuario> buscarTodos() {
        
        return repositorioUsuarios.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer idUsuario) {
        
        Optional<Usuario> optional = repositorioUsuarios.findById(idUsuario);
        if (optional.isPresent()) {
			return optional.get();
		}
		return null;

        
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        
        return repositorioUsuarios.findByUsername(username);
    }

    @Override
    public void setContraseña(Usuario usuario, String contraseña) {
        usuario.setPassword(contraseña);
        return;
    }

}

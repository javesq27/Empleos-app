package com.proyecto.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyecto.entities.Usuario;
import com.proyecto.proyecto.repositories.RepositorioUsuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioUsuarios implements IUsuarios {

    @Autowired
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
    public void setContrasenia(Usuario usuario, String contrasenia) {
        usuario.setPassword(contrasenia);
    }

    @Transactional
    @Override
    public int bloquear(int idUsuario) {
        int rows = repositorioUsuarios.lock(idUsuario);
		return rows;
    }

    @Transactional
    @Override
    public int activar(int idUsuario) {
        int rows = repositorioUsuarios.unlock(idUsuario);
		return rows;
    }

}

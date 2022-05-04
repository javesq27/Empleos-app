package com.proyecto.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyecto.entities.Categoria;
import com.proyecto.proyecto.repositories.RepositorioCategorias;

@Service
public class ServicioCategorias implements ICategorias {

    @Autowired
    private RepositorioCategorias repositorioCategorias;

    @Override
    public void guardar(Categoria categoria) {
        repositorioCategorias.save(categoria);
    }

    @Override
    public void eliminar(Integer idCategoria) {
        repositorioCategorias.deleteById(idCategoria);
    }

    @Override
    public List<Categoria> buscarTodas() {
        return repositorioCategorias.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer idCategoria) {
        Optional<Categoria> optional = repositorioCategorias.findById(idCategoria);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    
}

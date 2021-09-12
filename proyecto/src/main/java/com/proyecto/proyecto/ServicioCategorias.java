package com.proyecto.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioCategorias implements ICategorias {

    @Autowired
    RepositorioCategorias repositorioCategorias;

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
    public Optional<Categoria>  buscarPorId(Integer idCategoria) {
        return repositorioCategorias.findById(idCategoria);
    }
}

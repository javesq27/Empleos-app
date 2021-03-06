package com.proyecto.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyecto.entities.Vacante;
import com.proyecto.proyecto.repositories.RepositorioVacantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ServicioVacantes implements IVacantes {

    @Autowired
    private RepositorioVacantes repoVacantes;

    @Override
    public void guardar(Vacante vacante) {
        repoVacantes.save(vacante);
        
    }

    @Override
    public void eliminar(Integer idVacante) {
        repoVacantes.deleteById(idVacante);
        
    }

    @Override
    public List<Vacante> buscarTodas() {
        return repoVacantes.findAll();
    }

    @Override
    public Vacante buscarPorId(Integer idVacante) {
        Optional<Vacante> optional = repoVacantes.findById(idVacante);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Vacante> buscarDestacadas() {
    
        return repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
    }

    @Override
    public List<Vacante> buscarByExample(Example<Vacante> example) {
        
        return repoVacantes.findAll(example);
    }
    
}

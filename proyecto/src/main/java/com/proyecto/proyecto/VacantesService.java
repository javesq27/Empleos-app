package com.proyecto.proyecto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class VacantesService implements IVacantesImplement {

    @Autowired
    private VacantesRepository repoVacantes;

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
    
}

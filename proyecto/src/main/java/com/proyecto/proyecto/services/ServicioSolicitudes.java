package com.proyecto.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyecto.entities.Solicitud;
import com.proyecto.proyecto.repositories.RepositorioSolicitudes;

@Service
public class ServicioSolicitudes implements ISolicitudes{
    @Autowired
    RepositorioSolicitudes repositorioSolicitudes;

    @Autowired
    ServicioMails servicioMails;

    @Override
    public void guardar(Solicitud solicitud) {
        repositorioSolicitudes.save(solicitud);
    }

    @Override
    public void revisar(int idSolicitud){
        Solicitud solicitud = repositorioSolicitudes.getById(idSolicitud);
        solicitud.setObservador(servicioMails);
        solicitud.setRevisada(true);
        solicitud.notificar();
    }

    @Override
    public void eliminar(Integer idSolicitud) {
        repositorioSolicitudes.deleteById(idSolicitud);
    }

    @Override
    public List<Solicitud> buscarTodas() {
        return repositorioSolicitudes.findAll();
    }

    @Override
    public Optional<Solicitud> buscarPorId(Integer idSolicitud) {
        return repositorioSolicitudes.findById(idSolicitud);
    }
}

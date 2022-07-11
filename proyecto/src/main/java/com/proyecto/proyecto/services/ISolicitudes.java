package com.proyecto.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.proyecto.entities.Solicitud;

public interface ISolicitudes {
    void guardar(Solicitud solicitud);

    void revisar(int idSolicitud, ServicioMails servicioMails);
    void eliminar(Integer idSolicitud);
    List<Solicitud> buscarTodas();
    Optional<Solicitud> buscarPorId(Integer idSolicitud);
}

package com.proyecto.proyecto;

import java.util.List;
import java.util.Optional;

public interface ISolicitudes {
    void guardar(Solicitud solicitud);
    void eliminar(Integer idSolicitud);
    List<Solicitud> buscarTodas();
    Optional<Solicitud> buscarPorId(Integer idSolicitud);
}

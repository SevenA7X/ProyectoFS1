package com.example.Notificaciones.service;

import com.biblioteca.notificacion.model.Notificacion;
import com.biblioteca.notificacion.model.dto.NotificacionRequestDTO;
import java.util.List;

public interface NotificacionService {
    
    Notificacion crearNotificacion(NotificacionRequestDTO request);
    List<Notificacion> obtenerNotificacionesPorUsuario(Long usuarioId);
    List<Notificacion> obtenerNotificacionesNoLeidas(Long usuarioId);
    Notificacion marcarComoLeida(Long notificacionId);
    void marcarTodasComoLeidas(Long usuarioId);
    void eliminarNotificacion(Long id);
    long contarNoLeidas(Long usuarioId);
    void enviarNotificacionCompraExitosa(Long usuarioId, String juegoNombre);
    void enviarNotificacionLicenciaActivada(Long usuarioId, String juegoNombre);
    void enviarNotificacionResenaModerada(Long usuarioId, String juegoNombre, boolean aceptada);
}

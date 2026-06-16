package com.example.Notificaciones.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Notificaciones.modelo.NotificacionModelo;
import com.example.Notificaciones.repository.NotificacionRepository;
import com.example.Notificaciones.dto.NotificacionesDTO;

@Service
public class NotificacionService {

    private static final Logger log = LoggerFactory.getLogger(NotificacionService.class);

    @Autowired
    private NotificacionRepository notificacionRepository;

    public NotificacionesDTO enviarNotificacion(NotificacionesDTO dto) {
        log.info("Creando notificación de tipo {} para el usuario ID: {}", dto.getTipo(), dto.getUsuarioId());
        
        NotificacionModelo notificacion = new NotificacionModelo();
        notificacion.setUsuarioId(dto.getUsuarioId());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setTipo(dto.getTipo());
        
        NotificacionModelo guardada = notificacionRepository.save(notificacion);
        dto.setId(guardada.getId());
        return dto;
    }
}

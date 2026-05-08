package com.example.Notificaciones.service;

import com.example.Notificaciones.modelo.NotificacionDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class NotificacionService {

    public void procesarNotificacion(NotificacionDTO dto) {
        System.out.println("Enviando notificación para el usuario: " + dto.getUsuarioId());
    }

    public List<NotificacionDTO> obtenerPorUsuario(Long usuarioId) {
        return new ArrayList<>(); 
    }
}

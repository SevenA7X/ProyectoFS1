package com.example.Notificaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.Notificaciones.dto.NotificacionesDTO;
import com.example.Notificaciones.service.NotificacionService;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<NotificacionesDTO> enviar(@Valid @RequestBody NotificacionesDTO dto) {
        NotificacionesDTO nuevaNotificacion = notificacionService.enviarNotificacion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNotificacion);
    }
}


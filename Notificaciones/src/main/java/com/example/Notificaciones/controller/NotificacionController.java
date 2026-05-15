package com.example.Notificaciones.controller;

import com.example.Notificaciones.modelo.NotificacionDTO;
import com.example.Notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(@Valid @RequestBody NotificacionDTO dto) {
        // IE 2.3.2: Aquí podrías agregar un log.info("Recibida petición para usuario {}", dto.getUsuarioId());
        service.procesarNotificacion(dto);
        return ResponseEntity.ok("Notificación enviada al usuario " + dto.getUsuarioId());
    }

    @GetMapping("/historial/{usuarioId}")
    public List<NotificacionDTO> verHistorial(@PathVariable Long usuarioId) {
        return service.obtenerPorUsuario(usuarioId);
    }
}


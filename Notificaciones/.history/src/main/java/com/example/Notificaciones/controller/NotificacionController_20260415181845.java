package com.example.Notificaciones.controller;

import com.example.Notificaciones.modelo.NotificacionModelo;
import com.example.Notificaciones.modelo.dto.NotificacionRequestDTO;
import com.example.Notificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    
    @Autowired
    private NotificacionService notificacionService;
    
    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@RequestBody NotificacionRequestDTO request) {
        return ResponseEntity.ok(notificacionService.crearNotificacion(request));
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> obtenerNotificaciones(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.obtenerNotificacionesPorUsuario(usuarioId));
    }
    
    @GetMapping("/usuario/{usuarioId}/no-leidas")
    public ResponseEntity<List<Notificacion>> obtenerNoLeidas(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.obtenerNotificacionesNoLeidas(usuarioId));
    }
    
    @PutMapping("/{id}/leer")
    public ResponseEntity<Notificacion> marcarComoLeida(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.marcarComoLeida(id));
    }
    
    @PutMapping("/usuario/{usuarioId}/leer-todas")
    public ResponseEntity<Void> marcarTodasComoLeidas(@PathVariable Long usuarioId) {
        notificacionService.marcarTodasComoLeidas(usuarioId);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Long id) {
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/usuario/{usuarioId}/contador-no-leidas")
    public ResponseEntity<Long> contarNoLeidas(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.contarNoLeidas(usuarioId));
    }
    
    @PostMapping("/compras/exitosa")
    public ResponseEntity<Void> notificarCompraExitosa(@RequestParam Long usuarioId, @RequestParam String juegoNombre) {
        notificacionService.enviarNotificacionCompraExitosa(usuarioId, juegoNombre);
        return ResponseEntity.ok().build();
    }
}

package com.example.Estadisticas_Uso.controller;

import com.biblioteca.estadistica.model.EventoEstadistica;
import com.biblioteca.estadistica.model.EstadisticasGlobales;
import com.biblioteca.estadistica.model.dto.EventoRequestDTO;
import com.biblioteca.estadistica.model.dto.EstadisticasJuegoDTO;
import com.biblioteca.estadistica.model.dto.EstadisticasUsuarioDTO;
import com.biblioteca.estadistica.service.EstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaController {
    
    @Autowired
    private EstadisticaService estadisticaService;
    
    @PostMapping("/eventos")
    public ResponseEntity<EventoEstadistica> registrarEvento(@RequestBody EventoRequestDTO evento) {
        return ResponseEntity.ok(estadisticaService.registrarEvento(evento));
    }
    
    @GetMapping("/globales")
    public ResponseEntity<EstadisticasGlobales> obtenerEstadisticasGlobales() {
        return ResponseEntity.ok(estadisticaService.obtenerEstadisticasGlobales());
    }
    
    @GetMapping("/top/juegos-vendidos")
    public ResponseEntity<List<EstadisticasJuegoDTO>> obtenerTopJuegosMasVendidos() {
        return ResponseEntity.ok(estadisticaService.obtenerTopJuegosMasVendidos());
    }
    
    @GetMapping("/top/juegos-descargados")
    public ResponseEntity<List<EstadisticasJuegoDTO>> obtenerTopJuegosMasDescargados() {
        return ResponseEntity.ok(estadisticaService.obtenerTopJuegosMasDescargados());
    }
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<EstadisticasUsuarioDTO> obtenerEstadisticasUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(estadisticaService.obtenerEstadisticasUsuario(usuarioId));
    }
    
    @GetMapping("/eventos/por-tipo")
    public ResponseEntity<Map<String, Long>> obtenerEventosPorTipo() {
        return ResponseEntity.ok(estadisticaService.obtenerEventosPorTipo());
    }
    
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> obtenerDashboard() {
        return ResponseEntity.ok(estadisticaService.obtenerDashboardEstadisticas());
    }
    
    @GetMapping("/eventos/recientes")
    public ResponseEntity<List<EventoEstadistica>> obtenerEventosRecientes(@RequestParam(defaultValue = "50") int limite) {
        return ResponseEntity.ok(estadisticaService.obtenerEventosRecientes(limite));
    }
}

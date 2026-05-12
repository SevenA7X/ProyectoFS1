package com.example.Estadisticas_Uso.controller;

import com.example.Estadisticas_Uso.modelo.Estadistica;
import com.example.Estadisticas_Uso.service.EstadisticaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/estadisticas")
public class EstadisticaController {

    private final EstadisticaService service;

    public EstadisticaController(EstadisticaService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Estadistica> registrarAccion(@RequestBody Estadistica est) {
        // IE 2.1.2: Retornos JSON coherentes y estados HTTP correctos
        return new ResponseEntity<>(service.guardar(est), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Map<String, Object>> reporteUsuario(@PathVariable Long idUsuario) {
        // IE 2.4.2: Configura endpoints que consumen datos remotos
        return ResponseEntity.ok(service.generarReporteDetallado(idUsuario));
    }
}

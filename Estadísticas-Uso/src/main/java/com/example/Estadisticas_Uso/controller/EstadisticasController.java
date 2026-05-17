package com.example.Estadisticas_Uso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Estadisticas_Uso.modelo.Estadistica;
import com.example.Estadisticas_Uso.service.EstadisticasService;

@RestController
@RequestMapping("/api/v1/estadisticas")
public class EstadisticasController {

    @Autowired
    private EstadisticasService estadisticasService;

    @PostMapping("/generar-reporte")
    public ResponseEntity<Estadistica> generarReporte() {
        Estadistica nuevoReporte = estadisticasService.generarReporteDePopularidad();
        return ResponseEntity.ok(nuevoReporte);
    }
}
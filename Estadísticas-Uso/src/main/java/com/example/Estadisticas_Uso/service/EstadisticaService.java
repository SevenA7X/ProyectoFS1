package com.example.Estadisticas_Uso.service;

import com.example.Estadisticas_Uso.modelo.Estadistica;
import com.example.Estadisticas_Uso.repository.EstadisticaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EstadisticaService {

    private final EstadisticaRepository repository;
    private final WebClient.Builder webClientBuilder;

    public EstadisticaService(EstadisticaRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.webClientBuilder = webClientBuilder;
    }

    // 1. Método GUARDAR (Esto quita el primer error rojo)
    public Estadistica guardar(Estadistica est) {
        log.info("Registrando nueva acción estadística: {}", est.getAccion());
        est.setFecha(LocalDateTime.now()); // IE 2.2.1: Regla de negocio (asignar fecha actual)
        return repository.save(est);
    }

    // 2. Método REPORTE DETALLADO (Esto quita el segundo error rojo e implementa IE 2.4.1)
    public Map<String, Object> generarReporteDetallado(Long idUsuario) {
        log.info("Generando reporte para usuario ID: {}. Consultando datos remotos...", idUsuario);

        // Llamada al Microservicio de Usuarios (Puerto 8081) usando WebClient
        Object datosUsuario = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/usuarios/" + idUsuario)
                .retrieve()
                .bodyToMono(Object.class)
                .block(); // Bloqueante para simplificar el flujo del proyecto fullstack 1

        Map<String, Object> reporte = new HashMap<>();
        reporte.put("usuario", datosUsuario);
        reporte.put("total_acciones", repository.countByUsuarioId(idUsuario));
        reporte.put("fecha_reporte", LocalDateTime.now());

        return reporte;
    }
}
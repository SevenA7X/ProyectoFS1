package com.example.Estadisticas_Uso.service;

import com.biblioteca.estadistica.model.EventoEstadistica;
import com.biblioteca.estadistica.model.EstadisticasGlobales;
import com.biblioteca.estadistica.model.dto.EventoRequestDTO;
import com.biblioteca.estadistica.model.dto.EstadisticasJuegoDTO;
import com.biblioteca.estadistica.model.dto.EstadisticasUsuarioDTO;
import java.util.List;
import java.util.Map;

public interface EstadisticaService {
    EventoEstadistica registrarEvento(EventoRequestDTO evento);
    EstadisticasGlobales obtenerEstadisticasGlobales();
    List<EstadisticasJuegoDTO> obtenerTopJuegosMasVendidos();
    List<EstadisticasJuegoDTO> obtenerTopJuegosMasDescargados();
    EstadisticasUsuarioDTO obtenerEstadisticasUsuario(Long usuarioId);
    Map<String, Long> obtenerEventosPorTipo();
    Map<String, Object> obtenerDashboardEstadisticas();
    List<EventoEstadistica> obtenerEventosRecientes(int limite);
}
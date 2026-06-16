package com.example.Estadisticas_Uso.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Estadisticas_Uso.client.ResenaFeignClient;
import com.example.Estadisticas_Uso.dto.EstadisticasDTO;
import com.example.Estadisticas_Uso.modelo.Estadistica;
import com.example.Estadisticas_Uso.repository.EstadisticasRepository;

@Service
public class EstadisticasService {

    private static final Logger log = LoggerFactory.getLogger(EstadisticasService.class);

    @Autowired
    private ResenaFeignClient resenaFeignClient;

    @Autowired
    private EstadisticasRepository estadisticasRepository;

    public Estadistica generarReporteDePopularidad() {
        log.info("Iniciando extracción de datos de tendencias desde MS Reseñas.");
        List<EstadisticasDTO> resenas;

        try {
            resenas = resenaFeignClient.obtenerTodasLasResenas();
            log.info("Datos obtenidos exitosamente de MS Reseñas. Total de registros: {}", resenas.size());
        } catch (Exception e) { // ◄ Cambiado a Exception genérica para evitar el import fallido de feign
            log.error("Error en comunicación externa al recopilar reseñas: {}", e.getMessage());
            throw new RuntimeException("Error al conectar con el microservicio de reseñas: " + e.getMessage());
        }

        Estadistica reporte = new Estadistica();
        reporte.setNombreReporte("Reporte General de Rendimiento y Valoraciones");
        reporte.setTotalItemsAnalizados(resenas.size());
        
        return estadisticasRepository.save(reporte);
    }
}
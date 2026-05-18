package com.example.Estadisticas_Uso.client;

import com.example.Estadisticas_Uso.dto.EstadisticasDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "ms-resena", url = "http://localhost:8082/api/v1/resenas", fallback = ResenaFeignClientFallback.class)
public interface ResenaFeignClient {

    @GetMapping
    List<EstadisticasDTO> obtenerTodasLasResenas();
}

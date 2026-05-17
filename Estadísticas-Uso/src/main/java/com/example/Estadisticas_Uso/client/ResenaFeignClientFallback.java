package com.example.Estadisticas_Uso.client;

import org.springframework.stereotype.Component;
import com.example.Estadisticas_Uso.dto.EstadisticasDTO;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResenaFeignClientFallback implements ResenaFeignClient {

    @Override
    public List<EstadisticasDTO> obtenerTodasLasResenas() {
        // Fallback básico: Devuelve lista vacía en caso de que ms-resena esté caído
        return new ArrayList<>();
    }
}

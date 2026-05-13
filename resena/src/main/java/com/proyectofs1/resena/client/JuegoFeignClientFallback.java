package com.proyectofs1.resena.client;

import org.springframework.stereotype.Component;
import com.proyectofs1.resena.dto.JuegoValidacionDTO;

@Component
public class JuegoFeignClientFallback implements JuegoFeignClient {

    @Override
    public JuegoValidacionDTO obtenerJuegoPorId(Long id) {
        // En lugar de que el sistema falle si Catálogo está caído, 
        // devolvemos un DTO vacío o con datos por defecto.
        JuegoValidacionDTO juegoPorDefecto = new JuegoValidacionDTO();
        // Si su DTO lo permite, puede asignarle el ID para que no sea totalmente nulo
        // juegoPorDefecto.setId(id); 
        
        return juegoPorDefecto;
    }
}
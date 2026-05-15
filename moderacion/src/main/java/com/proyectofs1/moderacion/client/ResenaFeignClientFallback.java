package com.proyectofs1.moderacion.client;

import org.springframework.stereotype.Component;
// Importe aquí el DTO que use su cliente, por ejemplo:
// import com.proyectofs1.moderacion.dto.ResenaDTO;

@Component
public class ResenaFeignClientFallback implements ResenaFeignClient {

    // Asegúrese de que este método sea EXACTAMENTE IGUAL al que tiene en ResenaFeignClient.java
    @Override
    public Object obtenerResenaPorId(Long id) { 
        // Retornamos un objeto vacío o nulo controlado
        // Ejemplo: return new ResenaDTO();
        return null; // Cambie 'Object' y 'null' por el DTO correcto que devuelva su método.
    }
}
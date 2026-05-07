package com.proyectofs1.moderacion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "resena-service", url = "http://localhost:8082/api/v1/resenas")
public interface ResenaFeignClient {
    @GetMapping("/{id}")
    Object obtenerResenaPorId(@PathVariable("id") Long id);
}
package com.proyectofs1.moderacion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-resena", url = "http://localhost:8082/api/v1/resenas", fallback = ResenaFeignClientFallback.class)
public interface ResenaFeignClient {
    @GetMapping("/{id}")
    Object obtenerResenaPorId(@PathVariable("id") Long id);
}
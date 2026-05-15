package com.proyectofs1.resena.client;

import com.proyectofs1.resena.dto.JuegoValidacionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// "name" es un identificador interno. "url" es la ruta EXACTA donde escucha el MS Catálogo.

@FeignClient(name = "ms-catalogo", url = "http://localhost:8081/api/v1/juegos", fallback = JuegoFeignClientFallback.class)
public interface JuegoFeignClient {

    // Esto simula que estamos llamando al controlador de Catálogo
    @GetMapping("/{id}")
    JuegoValidacionDTO obtenerJuegoPorId(@PathVariable("id") Long id);
}


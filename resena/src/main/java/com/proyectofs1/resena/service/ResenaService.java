package com.proyectofs1.resena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import feign.FeignException; // Importación necesaria para atrapar errores de Feign

import com.proyectofs1.resena.model.Resena;
import com.proyectofs1.resena.repository.ResenaRepository;
import com.proyectofs1.resena.client.JuegoFeignClient; // Importación de su cliente Feign
import com.proyectofs1.resena.dto.JuegoValidacionDTO; // Importación de su DTO

@Service
@Transactional
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private JuegoFeignClient juegoFeignClient; // Inyección del cliente Feign

    public List<Resena> findAll() {
        return resenaRepository.findAll();
    }

    public Resena findById(Long id) {
        return resenaRepository.findById(id).get();
    }

    public Resena save(Resena resena) {
        // Validación síncrona mediante OpenFeign antes de persistir los datos
        try {
            JuegoValidacionDTO juego = juegoFeignClient.obtenerJuegoPorId(resena.getJuegoId());
        } catch (FeignException.NotFound e) {
            // Manejo del error 404 si el MS Catálogo no encuentra el ID
            throw new IllegalArgumentException("No se puede crear la reseña: El videojuego con ID " + resena.getJuegoId() + " no existe en el catálogo.");
        } catch (FeignException e) {
            // Manejo de errores de conexión o errores 500 del MS Catálogo
            throw new RuntimeException("Error de comunicación con el servicio de Catálogo: " + e.getMessage());
        }

        // Si la ejecución llega a este punto, la validación fue exitosa
        return resenaRepository.save(resena);
    }

    public void deleteById(Long id) {
        resenaRepository.deleteById(id);
    }
}
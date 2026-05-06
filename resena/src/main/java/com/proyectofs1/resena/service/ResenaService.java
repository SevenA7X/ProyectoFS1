package com.proyectofs1.resena.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import feign.FeignException;

import com.proyectofs1.resena.model.Resena;
import com.proyectofs1.resena.repository.ResenaRepository;
import com.proyectofs1.resena.client.JuegoFeignClient;
import com.proyectofs1.resena.dto.JuegoValidacionDTO;
import com.proyectofs1.resena.dto.ResenaDTO;

@Service
@Transactional
public class ResenaService {

    private static final Logger log = LoggerFactory.getLogger(ResenaService.class);

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private JuegoFeignClient juegoFeignClient;

    public List<ResenaDTO> findAll() {
        log.info("Consultando todas las reseñas en la base de datos.");
        return resenaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResenaDTO findById(Long id) {
        log.info("Iniciando búsqueda de reseña con ID: {}", id);
        Optional<Resena> resena = resenaRepository.findById(id);
        if (resena.isPresent()) {
            log.info("Reseña con ID: {} encontrada exitosamente.", id);
            return convertToDTO(resena.get());
        }
        log.warn("No se encontró ninguna reseña con el ID: {}", id);
        return null;
    }

    public ResenaDTO save(ResenaDTO resenaDTO) {
        log.info("Iniciando proceso de guardado para reseña del juego ID: {}", resenaDTO.getJuegoId());
        
        // Validación síncrona mediante OpenFeign antes de persistir los datos
        try {
            log.info("Validando existencia del juego ID: {} en MS Catálogo", resenaDTO.getJuegoId());
            JuegoValidacionDTO juego = juegoFeignClient.obtenerJuegoPorId(resenaDTO.getJuegoId());
            log.info("Validación exitosa. El juego existe.");
        } catch (FeignException.NotFound e) {
            log.error("Error de validación: El videojuego con ID {} no existe.", resenaDTO.getJuegoId());
            throw new IllegalArgumentException("No se puede crear la reseña: El videojuego con ID " + resenaDTO.getJuegoId() + " no existe en el catálogo.");
        } catch (FeignException e) {
            log.error("Error de comunicación con MS Catálogo: {}", e.getMessage());
            throw new RuntimeException("Error de comunicación con el servicio de Catálogo: " + e.getMessage());
        }

        // Si la ejecución llega a este punto, la validación fue exitosa
        Resena resena = convertToEntity(resenaDTO);
        Resena resenaGuardada = resenaRepository.save(resena);
        log.info("Reseña guardada exitosamente con ID asignado: {}", resenaGuardada.getId());
        return convertToDTO(resenaGuardada);
    }

    public void deleteById(Long id) {
    log.info("Ejecutando eliminación de la reseña con ID: {}", id);
    if (!resenaRepository.existsById(id)) {
        log.warn("Fallo al eliminar: No existe reseña con ID {}", id);
        throw new IllegalArgumentException("No se encontró la reseña a eliminar.");
    }
    resenaRepository.deleteById(id);
    log.info("Reseña con ID: {} eliminada correctamente.", id);
}

    // --- Métodos de Conversión ---

    private ResenaDTO convertToDTO(Resena resena) {
        ResenaDTO dto = new ResenaDTO();
        dto.setId(resena.getId());
        dto.setUsuarioId(resena.getUsuarioId());
        dto.setJuegoId(resena.getJuegoId());
        dto.setComentario(resena.getComentario());
        dto.setPuntuacion(resena.getPuntuacion());
        dto.setEstado(resena.getEstado());
        return dto;
    }

    private Resena convertToEntity(ResenaDTO dto) {
        Resena resena = new Resena();
        resena.setId(dto.getId());
        resena.setUsuarioId(dto.getUsuarioId());
        resena.setJuegoId(dto.getJuegoId());
        resena.setComentario(dto.getComentario());
        resena.setPuntuacion(dto.getPuntuacion());
        resena.setEstado(dto.getEstado());
        return resena;
    }
}
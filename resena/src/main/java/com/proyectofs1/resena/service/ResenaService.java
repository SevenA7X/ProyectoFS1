package com.proyectofs1.resena.service;

import java.util.List;

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
                .toList();
    }

    public ResenaDTO findById(Long id) {
        log.info("Iniciando búsqueda de reseña con ID: {}", id);
        return resenaRepository.findById(id)
                .map(resena -> {
                    log.info("Reseña con ID: {} encontrada exitosamente.", id);
                    return convertToDTO(resena);
                })
                .orElseThrow(() -> {
                    log.warn("No se encontró ninguna reseña con el ID: {}", id);
                    return new IllegalArgumentException("Reseña no encontrada con el ID: " + id);
                });
    }

    public ResenaDTO save(ResenaDTO resenaDTO) {
        log.info("Iniciando proceso de guardado para reseña del juego ID: {}", resenaDTO.getJuegoId());
        
        try {
            log.info("Validando existencia del juego ID: {} en MS Catálogo", resenaDTO.getJuegoId());
            JuegoValidacionDTO juego = juegoFeignClient.obtenerJuegoPorId(resenaDTO.getJuegoId());
            log.info("Validación exitosa. El juego existe.");
        } catch (FeignException.NotFound e) {
            log.error("Error de validación: El videojuego con ID {} no existe.", resenaDTO.getJuegoId());
            throw new IllegalArgumentException("No se puede crear la reseña: El videojuego no existe en el catálogo.");
        } catch (FeignException e) {
            log.error("Error de comunicación con MS Catálogo: {}", e.getMessage());
            throw new RuntimeException("Error de comunicación con el servicio de Catálogo: " + e.getMessage());
        }

        Resena resena = convertToEntity(resenaDTO);
        Resena resenaGuardada = resenaRepository.save(resena);
        log.info("Reseña guardada exitosamente con ID asignado: {}", resenaGuardada.getId());
        return convertToDTO(resenaGuardada);
    }

    public ResenaDTO update(Long id, ResenaDTO resenaDTO) {
        log.info("Iniciando actualización lógica de la reseña ID: {}", id);

        Resena resenaExistente = resenaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se puede actualizar: La reseña con ID " + id + " no existe."));

        resenaExistente.setComentario(resenaDTO.getComentario());
        resenaExistente.setPuntuacion(resenaDTO.getPuntuacion());
        resenaExistente.setEstado(resenaDTO.getEstado());

        Resena resenaActualizada = resenaRepository.save(resenaExistente);
        log.info("Reseña ID: {} actualizada con éxito en la base de datos.", id);
        return convertToDTO(resenaActualizada);
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
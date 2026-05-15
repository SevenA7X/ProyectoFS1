package com.proyectofs1.moderacion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.proyectofs1.moderacion.client.ResenaFeignClient; // <-- Importación del Feign Client
import com.proyectofs1.moderacion.dto.ModeracionDTO;
import com.proyectofs1.moderacion.model.Moderacion;
import com.proyectofs1.moderacion.repository.ModeracionRepository;

@Service
@Transactional
public class ModeracionService {

    private static final Logger log = LoggerFactory.getLogger(ModeracionService.class);

    @Autowired
    private ModeracionRepository moderacionRepository;

    @Autowired
    private ResenaFeignClient resenaFeignClient; // <-- Inyección del Feign Client

    public List<ModeracionDTO> findAll() {
        log.info("Consultando todos los registros de moderación.");
        return moderacionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ModeracionDTO findById(Long id) {
        log.info("Buscando registro de moderación con ID: {}", id);
        return moderacionRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> {
                    log.warn("Moderación no encontrada con ID: {}", id);
                    return new IllegalArgumentException("Registro de moderación no encontrado.");
                });
    }

    // <-- Método save actualizado con la lógica de Feign -->
    public ModeracionDTO save(ModeracionDTO moderacionDTO) {
        log.info("Validando existencia del contenido tipo: {} con ID: {}", 
                 moderacionDTO.getTipoContenido(), moderacionDTO.getContenidoId());
        
        // Validación síncrona mediante Feign
        if ("RESENA".equalsIgnoreCase(moderacionDTO.getTipoContenido())) {
            try {
                resenaFeignClient.obtenerResenaPorId(moderacionDTO.getContenidoId());
                log.info("Validación exitosa: La reseña existe.");
            } catch (Exception e) {
                log.error("Fallo de validación: El contenido no existe.");
                throw new IllegalArgumentException("No se puede moderar: El contenido con ID " + 
                                                   moderacionDTO.getContenidoId() + " no existe en el microservicio de Reseñas.");
            }
        }

        Moderacion moderacion = convertToEntity(moderacionDTO);
        Moderacion guardada = moderacionRepository.save(moderacion);
        log.info("Registro de moderación guardado con éxito (ID: {})", guardada.getId());
        return convertToDTO(guardada);
    }

    public ModeracionDTO update(Long id, ModeracionDTO moderacionDTO) {
        log.info("Actualizando registro de moderación ID: {}", id);
        Moderacion existente = moderacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se puede actualizar: Registro no encontrado."));

        existente.setResultado(moderacionDTO.getResultado());
        existente.setObservaciones(moderacionDTO.getObservaciones());
        // No actualizamos contenidoId ni tipoContenido por seguridad de negocio

        Moderacion actualizada = moderacionRepository.save(existente);
        log.info("Registro ID {} actualizado correctamente.", id);
        return convertToDTO(actualizada);
    }

    public void deleteById(Long id) {
        log.info("Intentando eliminar moderación ID: {}", id);
        if (!moderacionRepository.existsById(id)) {
            log.warn("Fallo al eliminar: ID {} no existe.", id);
            throw new IllegalArgumentException("El registro a eliminar no existe.");
        }
        moderacionRepository.deleteById(id);
        log.info("Registro ID {} eliminado con éxito.", id);
    }

    private ModeracionDTO convertToDTO(Moderacion moderacion) {
        ModeracionDTO dto = new ModeracionDTO();
        dto.setId(moderacion.getId());
        dto.setContenidoId(moderacion.getContenidoId());
        dto.setTipoContenido(moderacion.getTipoContenido());
        dto.setResultado(moderacion.getResultado());
        dto.setObservaciones(moderacion.getObservaciones());
        dto.setFechaRevision(moderacion.getFechaRevision());
        return dto;
    }

    private Moderacion convertToEntity(ModeracionDTO dto) {
        Moderacion moderacion = new Moderacion();
        moderacion.setId(dto.getId());
        moderacion.setContenidoId(dto.getContenidoId());
        moderacion.setTipoContenido(dto.getTipoContenido());
        moderacion.setResultado(dto.getResultado());
        moderacion.setObservaciones(dto.getObservaciones());
        if(dto.getFechaRevision() != null){
            moderacion.setFechaRevision(dto.getFechaRevision());
        }
        return moderacion;
    }
}
package com.proyectofs1.catalogo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofs1.catalogo.model.Juego;
import com.proyectofs1.catalogo.dto.JuegoDTO;
import com.proyectofs1.catalogo.repository.JuegoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JuegoService {
    
    @Autowired
    private JuegoRepository juegoRepository;

    public List<JuegoDTO> findAll() {
        return juegoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public JuegoDTO findById(Long id) {
        Optional<Juego> juego = juegoRepository.findById(id);
        if (juego.isPresent()) {
            return convertToDTO(juego.get());
        }
        return null; // Permite al Controller retornar 404 Not Found
    }
    
    public JuegoDTO save(JuegoDTO juegoDTO) {
        Juego juego = convertToEntity(juegoDTO);
        Juego juegoGuardado = juegoRepository.save(juego);
        return convertToDTO(juegoGuardado);
    }
    
    public void deleteById(Long id) {
        juegoRepository.deleteById(id);
    }

    public List<JuegoDTO> buscarPorCategoria(String nombreCategoria) {
        return juegoRepository.findByCategoriaNombre(nombreCategoria).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JuegoDTO> buscarPorTitulo(String titulo) {
        return juegoRepository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JuegoDTO> buscarPorPresupuesto(double precioMaximo) {
        return juegoRepository.findByPrecioLessThanEqual(precioMaximo).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<JuegoDTO> buscarPorGeneroYPresupuesto(String genero, double precioMaximo) {
        return juegoRepository.buscarPorGeneroYPrecioMaximo(genero, precioMaximo).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<JuegoDTO> buscarOfertas() {
        return juegoRepository.buscarTop3OfertasNativo().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // --- Métodos Privados de Mapeo ---
    
    private JuegoDTO convertToDTO(Juego juego) {
    JuegoDTO dto = new JuegoDTO();
    dto.setId(juego.getId());
    dto.setTitulo(juego.getTitulo());
    dto.setPrecio(juego.getPrecio());
    
    // Mapeo de la relación (evita problemas de recursión)
    if (juego.getCategoria() != null) {
        dto.setNombreCategoria(juego.getCategoria().getNombre());
    }
    return dto;
    }

    private Juego convertToEntity(JuegoDTO dto) {
        Juego juego = new Juego();
        juego.setId(dto.getId());
        juego.setTitulo(dto.getTitulo());
        juego.setPrecio(dto.getPrecio());
        // Si su clase Juego tiene más atributos, asígnelos aquí
        return juego;
    }
}
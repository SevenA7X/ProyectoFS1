package com.proyectofs1.catalogo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectofs1.catalogo.model.Categoria;
import com.proyectofs1.catalogo.dto.CategoriaDTO;
import com.proyectofs1.catalogo.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO findById(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return convertToDTO(categoria.get());
        }
        return null;
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = convertToEntity(categoriaDTO);
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return convertToDTO(categoriaGuardada);
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    // --- Métodos de Conversión ---

    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNombre(categoria.getNombre());
        return dto;
    }

    private Categoria convertToEntity(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNombre(dto.getNombre());
        return categoria;
    }
}
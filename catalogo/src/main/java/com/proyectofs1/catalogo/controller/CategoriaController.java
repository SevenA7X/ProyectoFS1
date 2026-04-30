package com.proyectofs1.catalogo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectofs1.catalogo.dto.CategoriaDTO; // Importación del DTO
import com.proyectofs1.catalogo.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categorias = categoriaService.findAll();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO nuevaCategoria = categoriaService.save(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        CategoriaDTO categoriaDTO = categoriaService.findById(id);
        
        if (categoriaDTO != null) {
            return ResponseEntity.ok(categoriaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
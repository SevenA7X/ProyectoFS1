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
    // Método para ELIMINAR una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        // Llama al método que ya tienes en CategoriaService
        categoriaService.deleteById(id);
        
        // Retorna 204 No Content, que es el estándar cuando se borra algo exitosamente
        return ResponseEntity.noContent().build();
    }

    // Método para ACTUALIZAR una categoría (Opcional pero muy recomendado para un CRUD completo)
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        // 1. Primero verificamos que la categoría exista
        CategoriaDTO categoriaExistente = categoriaService.findById(id);
        if (categoriaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        
        // 2. Le asignamos el ID de la ruta para asegurar que no se cree una nueva
        categoriaDTO.setId(id);
        
        // 3. Guardamos los cambios (el método save de JPA actualiza si el ID ya existe)
        CategoriaDTO categoriaActualizada = categoriaService.save(categoriaDTO);
        
        return ResponseEntity.ok(categoriaActualizada);
    }
}
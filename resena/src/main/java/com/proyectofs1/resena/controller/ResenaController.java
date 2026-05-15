package com.proyectofs1.resena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.proyectofs1.resena.dto.ResenaDTO;
import com.proyectofs1.resena.service.ResenaService;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> findAll() {
        List<ResenaDTO> resenas = resenaService.findAll();
        if (resenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }

    @PostMapping
    public ResponseEntity<ResenaDTO> save(@Valid @RequestBody ResenaDTO resenaDTO) {
        ResenaDTO resenaNueva = resenaService.save(resenaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resenaNueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaDTO> findById(@PathVariable Long id) {
        ResenaDTO resena = resenaService.findById(id);
        return ResponseEntity.ok(resena);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResenaDTO> update(@PathVariable Long id, @Valid @RequestBody ResenaDTO resenaDTO) {
        ResenaDTO resenaActualizada = resenaService.update(id, resenaDTO);
        return ResponseEntity.ok(resenaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        resenaService.deleteById(id);
        return ResponseEntity.ok("Reseña Eliminada");
    }
}
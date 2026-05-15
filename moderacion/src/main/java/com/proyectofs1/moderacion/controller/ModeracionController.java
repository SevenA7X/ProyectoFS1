package com.proyectofs1.moderacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.proyectofs1.moderacion.dto.ModeracionDTO;
import com.proyectofs1.moderacion.service.ModeracionService;

@RestController
@RequestMapping("/api/v1/moderacion")
public class ModeracionController {

    @Autowired
    private ModeracionService moderacionService;

    @GetMapping
    public ResponseEntity<List<ModeracionDTO>> findAll() {
        List<ModeracionDTO> moderaciones = moderacionService.findAll();
        if (moderaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(moderaciones);
    }

    @PostMapping
    public ResponseEntity<ModeracionDTO> save(@Valid @RequestBody ModeracionDTO moderacionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moderacionService.save(moderacionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeracionDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(moderacionService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeracionDTO> update(@PathVariable Long id, @Valid @RequestBody ModeracionDTO moderacionDTO) {
        return ResponseEntity.ok(moderacionService.update(id, moderacionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        moderacionService.deleteById(id);
        return ResponseEntity.ok("Registro de moderación eliminado");
    }
}
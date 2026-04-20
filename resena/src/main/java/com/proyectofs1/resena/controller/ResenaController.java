package com.proyectofs1.resena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.proyectofs1.resena.model.Resena;
import com.proyectofs1.resena.service.ResenaService;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resena>> findAll() {
        List<Resena> resenas = resenaService.findAll();
        if (resenas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }

    @PostMapping
    public ResponseEntity<Resena> save(@Valid @RequestBody Resena resena) {
        Resena resenaNueva = resenaService.save(resena);
        return ResponseEntity.status(HttpStatus.CREATED).body(resenaNueva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> findById(@PathVariable Long id) {
        try {
            Resena resena = resenaService.findById(id);
            return ResponseEntity.ok(resena);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resena> save(@PathVariable Long id, @Valid @RequestBody Resena resena){
        try {
            // Asignación crucial para garantizar que se actualice el registro correcto
            resena.setId(id); 
            return ResponseEntity.ok(resenaService.save(resena));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id){
        resenaService.deleteById(id);
        return ResponseEntity.ok("Reseña Eliminada");
    }
}
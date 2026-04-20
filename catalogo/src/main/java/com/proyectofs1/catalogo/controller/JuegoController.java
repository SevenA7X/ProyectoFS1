package com.proyectofs1.catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectofs1.catalogo.model.Juego;
import com.proyectofs1.catalogo.service.JuegoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/juegos")
public class JuegoController {
    @Autowired
    private JuegoService juegoService;

    @GetMapping
    public ResponseEntity <List<Juego>> findAll(){
        List<Juego> juegos = juegoService.findAll();
        if(juegos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(juegos);

    }

    @PostMapping
    public ResponseEntity<Juego> save(@RequestBody Juego juego){
        Juego juegoNuevo = juegoService.save(juego);
        return ResponseEntity.status(HttpStatus.CREATED).body(juegoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juego> findById(@Valid@PathVariable Long id){
        try {
            Juego juego = juegoService.findById(id);
            return ResponseEntity.ok(juego);
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Juego> save(@PathVariable Long id, @Valid @RequestBody Juego juego){
        try {
            // Asignación crucial para garantizar que se actualice el registro correcto
            juego.setId(id); 
            return ResponseEntity.ok(juegoService.save(juego));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id){
        juegoService.deleteById(id);
        return ResponseEntity.ok("Juego Eliminado");
    }

}

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
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Juego> save(@Valid @RequestBody Juego juego){
        Juego juegoNuevo = juegoService.save(juego);
        return ResponseEntity.status(HttpStatus.CREATED).body(juegoNuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Juego> findById(@PathVariable Long id){
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
    
    // http://localhost:8081/api/v1/juegos/buscar?titulo=elden
    @GetMapping("/buscar")
    public ResponseEntity<List<Juego>> buscarPorTitulo(@RequestParam String titulo) {
        List<Juego> resultados = juegoService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(resultados);
    }

    // http://localhost:8081/api/v1/juegos/presupuesto?maximo=20000
    @GetMapping("/presupuesto")
    public ResponseEntity<List<Juego>> buscarPorPresupuesto(@RequestParam double maximo) {
        List<Juego> resultados = juegoService.buscarPorPresupuesto(maximo);
        return ResponseEntity.ok(resultados);
    }

    // Actualizado: http://localhost:8081/api/v1/juegos/filtro?categoria=RPG&maximo=50000
    @GetMapping("/filtro")
    public ResponseEntity<List<Juego>> buscarPorFiltros(
            @RequestParam String categoria, 
            @RequestParam double maximo) {
        // Se mantiene la llamada al método de su servicio, enviando la categoría recibida
        List<Juego> resultados = juegoService.buscarPorGeneroYPresupuesto(categoria, maximo);
        return ResponseEntity.ok(resultados);
    }
    
    // http://localhost:8081/api/v1/juegos/ofertas
    // Endpoint: GET /api/v1/juegos/ofertas
    @GetMapping("/ofertas")
    public ResponseEntity<List<Juego>> obtenerOfertas() {
        List<Juego> resultados = juegoService.buscarOfertas();
        if (resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultados);
    }
}
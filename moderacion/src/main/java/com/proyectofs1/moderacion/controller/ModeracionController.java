package com.proyectofs1.moderacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.proyectofs1.moderacion.model.Moderacion;
import com.proyectofs1.moderacion.service.ModeracionService;

// Le dice a Spring que esta clase va a recibir peticiones de internet (HTTP) 
// y que las respuestas se enviarán directamente como datos (en formato JSON).
@RestController

// Define la ruta o URL principal para comunicarse con este microservicio.
@RequestMapping("/api/v1/moderacion")
public class ModeracionController {

    // Conecta automáticamente este controlador con la capa de servicio.
    @Autowired
    private ModeracionService moderacionService;

    // Responde a peticiones GET (para leer o consultar datos).
    @GetMapping
    public ResponseEntity<List<Moderacion>> findAll() {
        List<Moderacion> moderaciones = moderacionService.findAll();
        if (moderaciones.isEmpty()) {
            // Si la base de datos está vacía, devuelve un código 204 (Sin contenido).
            return ResponseEntity.noContent().build();
        }
        // Si hay datos, devuelve un código 200 (OK) junto con la lista.
        return ResponseEntity.ok(moderaciones);
    }

    // Responde a peticiones POST (para crear o insertar nuevos datos).
    // @Valid verifica que los datos cumplan las reglas (como @NotNull) antes de entrar.
    // @RequestBody toma la información enviada en formato JSON y la convierte en un objeto Java.
    @PostMapping
    public ResponseEntity<Moderacion> save(@Valid @RequestBody Moderacion moderacion) {
        Moderacion moderacionNueva = moderacionService.save(moderacion);
        // Devuelve un código 201 (Creado) junto con el registro que se acaba de guardar.
        return ResponseEntity.status(HttpStatus.CREATED).body(moderacionNueva);
    }

    // Busca un solo registro leyendo el número de ID que viene en la URL.
    @GetMapping("/{id}")
    public ResponseEntity<Moderacion> findById(@PathVariable Long id) {
        try {
            Moderacion moderacion = moderacionService.findById(id);
            return ResponseEntity.ok(moderacion);
        } catch (Exception e) {
            // Si el ID no existe en la base de datos, devuelve un error 404 (No encontrado).
            return ResponseEntity.notFound().build();
        }
    }

    // Responde a peticiones PUT (para actualizar un registro que ya existe).
    @PutMapping("/{id}")
    public ResponseEntity<Moderacion> update(@PathVariable Long id, @Valid @RequestBody Moderacion moderacion) {
        try {
            // Este paso es crucial: se toma el ID de la URL y se le asigna al objeto.
            // Así nos aseguramos de que actualice el registro correcto y no cree uno nuevo.
            moderacion.setId(id);
            return ResponseEntity.ok(moderacionService.save(moderacion));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Responde a peticiones DELETE (para borrar información permanentemente).
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id) {
        moderacionService.deleteById(id);
        return ResponseEntity.ok("Registro de moderación eliminado");
    }
}
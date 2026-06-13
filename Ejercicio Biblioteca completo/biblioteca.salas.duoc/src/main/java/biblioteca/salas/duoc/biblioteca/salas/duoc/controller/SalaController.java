package biblioteca.salas.duoc.biblioteca.salas.duoc.controller;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Sala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@Tag(name = "Salas", description = "Operaciones relacionadas con las salas")
public class SalaController {
    @Autowired
    private SalaService salaService;

    @Schema
    @RequestBody(description = "Sala a crear", required = true)
    @Parameter(description = "id de la sala", required = true)
    @GetMapping
    @Operation(summary = "Obtener todas las salas", description = "Obtiene una lista de todas las salas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "Carrera no encontrada")
    })
    @ApiResponse(responseCode = "200", description = "Operación exitosa",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation =
    Sala.class)))
    public List<Sala> getAllSalas() {
        return salaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una sala por su id", description = "Obtiene una sala por su id")
    public Sala getSalaById(@PathVariable Integer id) {
        return salaService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear una nueva sala", description = "Crea una sala por su id")
    public Sala createSala(@RequestBody Sala sala) {
        return salaService.save(sala);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una sala", description = "Actualiza una sala por su id")
    public Sala updateSala(@PathVariable Integer id, @RequestBody Sala sala) {
        sala.setCodigo(id);
        return salaService.save(sala);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sala", description = "Elimina una sala por su id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sala eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Sala no encontrada")})
    public void deleteSala(@PathVariable Integer id) {
        salaService.deleteById(id);
    }
}

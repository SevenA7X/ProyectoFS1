package BibliotecaJuegos.LicenciasYDescargas.Controlador;

import BibliotecaJuegos.LicenciasYDescargas.dto.LicenciasYDescargasDTO;
import BibliotecaJuegos.LicenciasYDescargas.Servicio.Servicio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/licencias")
@Tag(name = "Licencias y Descargas", description = "Operaciones relacionadas con la emisión, consulta y gestión de licencias de juegos")
public class Controlador {

    @Autowired
    private Servicio servicio;

    @PostMapping("/generar")
    @Operation(summary = "Generar licencia por compra", description = "Emite de forma automática una nueva licencia vinculada a la compra de un videojuego.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Licencia generada con éxito (Sin contenido en la respuesta)"),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos")
    })
    public ResponseEntity<Void> generarLicenciaPorCompra(@RequestBody LicenciasYDescargasDTO dto) {
        log.info("Controlador Licencias: Generando licencia para Usuario {} y Juego {}", dto.getUsuarioID(), dto.getVideojuegoID());
        servicio.emitirLicenciaPorCompra(dto); 
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Listar todas las licencias", description = "Obtiene una lista completa de todas las licencias y descargas registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "204", description = "No hay licencias registradas en la base de datos")
    })
    public ResponseEntity<List<LicenciasYDescargasDTO>> listarLicencias() {
        log.info("Controlador Licencias: Petición GET recibida");
        List<LicenciasYDescargasDTO> lista = servicio.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar licencia por ID", description = "Obtiene los detalles de una licencia o registro de descarga específico según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "404", description = "Licencia no encontrada")
    })
    public ResponseEntity<LicenciasYDescargasDTO> buscarLicencia(@PathVariable Long id) {
        log.info("Controlador Licencias: Petición GET para ID {}", id);
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    @Operation(summary = "Agregar licencia manualmente", description = "Registra manualmente una nueva licencia en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Licencia registrada con éxito"),
        @ApiResponse(responseCode = "400", description = "Datos del cuerpo de la petición inválidos")
    })
    public ResponseEntity<LicenciasYDescargasDTO> agregarLicencia(@Valid @RequestBody LicenciasYDescargasDTO dto) {
        log.info("Controlador Licencias: Petición POST recibida");
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar licencia", description = "Revoca o elimina del sistema una licencia existente mediante su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Licencia eliminada con éxito (Sin contenido)"),
        @ApiResponse(responseCode = "404", description = "Licencia no encontrada para eliminar")
    })
    public ResponseEntity<Void> eliminarLicencia(@PathVariable Long id) {
        log.info("Controlador Licencias: Petición DELETE para ID {}", id);
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
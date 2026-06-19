package BibliotecaDigital.Pagos.Controlador;

import BibliotecaDigital.Pagos.dto.PagosDTO;
import BibliotecaDigital.Pagos.Servicio.Servicio;
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
@RequestMapping("/api/v1/pagos") 
@Tag(name = "Pagos", description = "Operaciones relacionadas con la gestión de pagos")
public class Controlador {

    @Autowired
    private Servicio servicio;

    @GetMapping
    @Operation(summary = "Obtener todos los pagos", description = "Obtiene una lista de todos los pagos registrados en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "204", description = "No hay pagos registrados en la BD")
    })
    public ResponseEntity<List<PagosDTO>> listarPagos() {
        log.info("Controlador: Petición GET recibida para listar pagos");
        List<PagosDTO> pagos = servicio.listarPagos();
        
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pago por ID", description = "Obtiene los detalles de un pago específico mediante su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<PagosDTO> obtenerPorId(@PathVariable Long id) {
        log.info("Controlador: Petición GET recibida para ID {}", id);
        PagosDTO pago = servicio.obtenerPagoPorId(id);
        return ResponseEntity.ok(pago);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo pago", description = "Registra un nuevo pago en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pago registrado con éxito"),
        @ApiResponse(responseCode = "400", description = "Datos del pago inválidos o incorrectos")
    })
    public ResponseEntity<PagosDTO> crearPago(@Valid @RequestBody PagosDTO pagosDTO) {
        log.info("Controlador: Petición POST recibida para nueva compra");
        PagosDTO guardado = servicio.guardarPago(pagosDTO);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pago existente", description = "Modifica los datos de un pago ya registrado usando su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pago actualizado con éxito"),
        @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado para actualizar")
    })
    public ResponseEntity<PagosDTO> actualizarPago(@PathVariable Long id, @Valid @RequestBody PagosDTO pagosDTO) {
        log.info("Controlador: Petición PUT recibida para ID {}", id);
        PagosDTO actualizado = servicio.actualizarPago(id, pagosDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pago", description = "Elimina un registro de pago del sistema mediante su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pago eliminado con éxito (Sin contenido)"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        log.info("Controlador: Petición DELETE recibida para ID {}", id);
        servicio.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
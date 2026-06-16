package BibliotecaDigital.Compras.Controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import BibliotecaDigital.Compras.dto.ComprasDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import BibliotecaDigital.Compras.Servicio.Servicio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/compras")
@Tag(name = "Compras", description = "Operaciones relacionadas con las compras")
public class Controlador {

    @Autowired
    private Servicio servicio;

    @GetMapping
    @Operation(summary = "Obtener todas las compras", description = "Obtiene una lista de todas las compras registradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "204", description = "No hay datos en la BD"),
        @ApiResponse(responseCode = "500", description = "El microservicio no esta iniciado, o hubo un error de conexion")
    })
    public ResponseEntity<List<ComprasDTO>> listarCompras() {
        log.info("Controlador Compras: Petición GET recibida");
        List<ComprasDTO> lista = servicio.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{compraID}")
    @Operation(summary = "Obtener compra por ID", description = "Obtiene una compra segun su ID de compra")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "404", description = "Compra no encontrada"),
        @ApiResponse(responseCode = "500", description = "El microservicio no esta iniciado, o hubo un error de conexion")
    })
    public ResponseEntity<ComprasDTO> buscarCompra(@PathVariable Long compraID) {
        log.info("Controlador Compras: Petición GET para ID {}", compraID);
        ComprasDTO com = servicio.findById(compraID);
        return ResponseEntity.ok(com);
    }

    @PostMapping
    @Operation(summary = "Agregar compra", description = "Agrega una nueva compra")
        @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "400", description = "Usuario invalido"),
        @ApiResponse(responseCode = "500", description = "El microservicio no esta iniciado, o hubo un error de conexion")
    })
    public ResponseEntity<ComprasDTO> agregarCompra(@Valid @RequestBody ComprasDTO ComprasDTO) {
        log.info("Controlador Compras: Petición POST recibida");
        ComprasDTO nuevaCompra = servicio.save(ComprasDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCompra);
    }

    @PutMapping("/{compraID}")
    @Operation(summary = "Modificar compra", description = "Modifica una compra registrada")
        @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "400", description = "Usuario invalido"),
        @ApiResponse(responseCode = "404", description = "Compra no encontrada para modificar"),
        @ApiResponse(responseCode = "500", description = "El microservicio no esta iniciado, o hubo un error de conexion")
    })
    public ResponseEntity<ComprasDTO> modificarCompra(@PathVariable Long compraID, @Valid @RequestBody ComprasDTO ComprasDTO) {
        log.info("Controlador Compras: Petición PUT para ID {}", compraID);
        ComprasDTO.setCompraID(compraID); 
        ComprasDTO actualizada = servicio.save(ComprasDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{compraID}")
    @Operation(summary = "Eliminar compra", description = "Elimina una compra registrada")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "404", description = "Compra no encontrada"),
        @ApiResponse(responseCode = "500", description = "El microservicio no esta iniciado, o hubo un error de conexion")
    })
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long compraID) {
        log.info("Controlador Compras: Petición DELETE para ID {}", compraID);
        servicio.delete(compraID);
        return ResponseEntity.noContent().build();
    }
}
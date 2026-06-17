package BibliotecaDigital.Compras.Controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import BibliotecaDigital.Compras.assemblers.CompraModelAssembler;
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

    @Autowired
    private CompraModelAssembler compraAssembler;

    @GetMapping
    @Operation(summary = "Obtener todas las compras", description = "Obtiene una lista de todas las compras registradas con navegación hipermedia (HATEOAS)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "204", description = "No hay datos en la BD")
    })
    public ResponseEntity<CollectionModel<EntityModel<ComprasDTO>>> listarCompras() {
        log.info("Controlador Compras: Petición GET recibida");
        List<ComprasDTO> lista = servicio.findAll();
        
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<ComprasDTO>> comprasConLinks = lista.stream()
                .map(compraAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<ComprasDTO>> contenedorGlobal = CollectionModel.of(comprasConLinks,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(Controlador.class).listarCompras()).withSelfRel()
        );

        return ResponseEntity.ok(contenedorGlobal);
    }

    @GetMapping("/{compraID}")
    @Operation(summary = "Obtener compra por ID", description = "Obtiene una compra según su ID de compra con navegación hipermedia (HATEOAS)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "404", description = "Compra no encontrada")
    })
    public ResponseEntity<EntityModel<ComprasDTO>> buscarCompra(@PathVariable Long compraID) {
        log.info("Controlador Compras: Petición GET para ID {}", compraID);
        
        ComprasDTO com = servicio.findById(compraID);
        
        EntityModel<ComprasDTO> modeloConLinks = compraAssembler.toModel(com);
        
        return ResponseEntity.ok(modeloConLinks);
    }
    
    @PostMapping
    @Operation(summary = "Agregar compra", description = "Agrega una nueva compra en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Compra registrada con éxito"),
        @ApiResponse(responseCode = "400", description = "Datos de la compra inválidos")
    })
    public ResponseEntity<EntityModel<ComprasDTO>> agregarCompra(@Valid @RequestBody ComprasDTO comprasDTO) {
    log.info("Controlador Compras: Petición POST recibida");
    ComprasDTO nuevaCompra = servicio.save(comprasDTO);
    
    EntityModel<ComprasDTO> modeloConLinks = compraAssembler.toModel(nuevaCompra);
    
    return ResponseEntity.status(HttpStatus.CREATED).body(modeloConLinks);
}

    @PutMapping("/{compraID}")
    @Operation(summary = "Modificar compra", description = "Modifica los datos de una compra registrada existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Compra modificada con éxito"),
        @ApiResponse(responseCode = "400", description = "Datos de modificación inválidos"),
        @ApiResponse(responseCode = "404", description = "Compra no encontrada para modificar")
    })
    public ResponseEntity<ComprasDTO> modificarCompra(@PathVariable Long compraID, @Valid @RequestBody ComprasDTO ComprasDTO) {
        log.info("Controlador Compras: Petición PUT para ID {}", compraID);
        ComprasDTO.setCompraID(compraID); 
        ComprasDTO actualizada = servicio.save(ComprasDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{compraID}")
    @Operation(summary = "Eliminar compra", description = "Elimina de forma permanente una compra registrada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Compra eliminada con éxito (Sin contenido)"),
        @ApiResponse(responseCode = "404", description = "Compra no encontrada")
    })
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long compraID) {
        log.info("Controlador Compras: Petición DELETE para ID {}", compraID);
        servicio.delete(compraID);
        return ResponseEntity.noContent().build();
    }
}
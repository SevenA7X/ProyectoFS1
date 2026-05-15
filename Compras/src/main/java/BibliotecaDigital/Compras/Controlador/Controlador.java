package BibliotecaDigital.Compras.Controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import BibliotecaDigital.Compras.dto.ComprasDTO;
import BibliotecaDigital.Compras.Servicio.Servicio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/compras")
public class Controlador {

    @Autowired
    private Servicio servicio;

    @GetMapping
    public ResponseEntity<List<ComprasDTO>> listarCompras() {
        log.info("Controlador Compras: Petición GET recibida");
        List<ComprasDTO> lista = servicio.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{compraID}")
    public ResponseEntity<ComprasDTO> buscarCompra(@PathVariable Long compraID) {
        log.info("Controlador Compras: Petición GET para ID {}", compraID);
        ComprasDTO com = servicio.findById(compraID);
        return ResponseEntity.ok(com);
    }

    @PostMapping
    public ResponseEntity<ComprasDTO> agregarCompra(@Valid @RequestBody ComprasDTO ComprasDTO) {
        log.info("Controlador Compras: Petición POST recibida");
        ComprasDTO nuevaCompra = servicio.save(ComprasDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCompra);
    }

    @PutMapping("/{compraID}")
    public ResponseEntity<ComprasDTO> modificarCompra(@PathVariable Long compraID, @Valid @RequestBody ComprasDTO ComprasDTO) {
        log.info("Controlador Compras: Petición PUT para ID {}", compraID);
        ComprasDTO.setCompraID(compraID); 
        ComprasDTO actualizada = servicio.save(ComprasDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{compraID}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long compraID) {
        log.info("Controlador Compras: Petición DELETE para ID {}", compraID);
        servicio.delete(compraID);
        return ResponseEntity.noContent().build();
    }
}
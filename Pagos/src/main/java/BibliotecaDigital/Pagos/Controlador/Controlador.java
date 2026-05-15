package BibliotecaDigital.Pagos.Controlador;

import BibliotecaDigital.Pagos.dto.PagosDTO;
import BibliotecaDigital.Pagos.Servicio.Servicio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/pagos") // Es buena práctica usar versiones como v1
public class Controlador {

    @Autowired
    private Servicio servicio;

    @GetMapping
    public ResponseEntity<List<PagosDTO>> listarPagos() {
        log.info("Controlador: Petición GET recibida para listar pagos");
        List<PagosDTO> pagos = servicio.listarPagos();
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagosDTO> obtenerPorId(@PathVariable Long id) {
        log.info("Controlador: Petición GET recibida para ID {}", id);
        PagosDTO pago = servicio.obtenerPagoPorId(id);
        return ResponseEntity.ok(pago);
    }

    @PostMapping
    public ResponseEntity<PagosDTO> crearPago(@Valid @RequestBody PagosDTO pagosDTO) {
        log.info("Controlador: Petición POST recibida para nueva compra");
        PagosDTO guardado = servicio.guardarPago(pagosDTO);
        return new ResponseEntity<>(guardado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagosDTO> actualizarPago(@PathVariable Long id, @Valid @RequestBody PagosDTO pagosDTO) {
        log.info("Controlador: Petición PUT recibida para ID {}", id);
        PagosDTO actualizado = servicio.actualizarPago(id, pagosDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long id) {
        log.info("Controlador: Petición DELETE recibida para ID {}", id);
        servicio.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}
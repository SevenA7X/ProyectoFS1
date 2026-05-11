package BibliotecaJuegos.HistorialCompras.Controlador;

import BibliotecaJuegos.HistorialCompras.dto.HistorialComprasDTO;
import BibliotecaJuegos.HistorialCompras.Servicio.Servicio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/historial")
public class Controlador {

    @Autowired
    private Servicio servicio;

    @GetMapping
    public ResponseEntity<List<HistorialComprasDTO>> listarHistorial() {
        log.info("Controlador Historial: Petición GET recibida");
        List<HistorialComprasDTO> lista = servicio.listarTodo();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialComprasDTO> buscarHistorial(@PathVariable Long id) {
        log.info("Controlador Historial: Petición GET para ID {}", id);
        HistorialComprasDTO resultado = servicio.buscarPorId(id);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity<HistorialComprasDTO> agregarHistorial(@Valid @RequestBody HistorialComprasDTO dto) {
        log.info("Controlador Historial: Petición POST recibida");
        HistorialComprasDTO guardado = servicio.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable Long id) {
        log.info("Controlador Historial: Petición DELETE para ID {}", id);
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
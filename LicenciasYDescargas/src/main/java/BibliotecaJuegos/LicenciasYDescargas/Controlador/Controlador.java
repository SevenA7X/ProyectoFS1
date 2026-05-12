package BibliotecaJuegos.LicenciasYDescargas.Controlador;

import BibliotecaJuegos.LicenciasYDescargas.dto.LicenciasYDescargasDTO;
import BibliotecaJuegos.LicenciasYDescargas.Servicio.Servicio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/licencias")
public class Controlador {

    @Autowired
    private Servicio servicio;

    @GetMapping
    public ResponseEntity<List<LicenciasYDescargasDTO>> listarLicencias() {
        log.info("Controlador Licencias: Petición GET recibida");
        List<LicenciasYDescargasDTO> lista = servicio.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicenciasYDescargasDTO> buscarLicencia(@PathVariable Long id) {
        log.info("Controlador Licencias: Petición GET para ID {}", id);
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public ResponseEntity<LicenciasYDescargasDTO> agregarLicencia(@Valid @RequestBody LicenciasYDescargasDTO dto) {
        log.info("Controlador Licencias: Petición POST recibida");
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLicencia(@PathVariable Long id) {
        log.info("Controlador Licencias: Petición DELETE para ID {}", id);
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
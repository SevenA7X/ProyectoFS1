package BibliotecaDigital.Pagos.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BibliotecaDigital.Pagos.Modelo.Pagos;
import BibliotecaDigital.Pagos.Servicio.Servicio;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/pagos")
public class Controlador {
    @Autowired
    private Servicio servicio;

    @GetMapping
    public ResponseEntity<List<Pagos>> listarPagos(){
        log.info("Consultando lista completa de pagos");
        List<Pagos> pagos = servicio.listarPagos();
        if (pagos.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.ok(pagos);
        }
    }

    @GetMapping("/{pagoID}")
    public ResponseEntity<Pagos> obtenerPagoPorId(@Valid @PathVariable Long pagoID){
        log.info("Buscando pago con ID: {}", pagoID);
        Pagos pago = servicio.obtenerPagoPorId(pagoID);
        if (pago == null) {
            return ResponseEntity.notFound().build(); 
        } else {
            return ResponseEntity.ok(pago);
        }
    }

    @PostMapping
    public ResponseEntity<Pagos> guardarPago(@Valid @RequestBody Pagos pagos){
        log.info("Registrando nuevo pago para compra ID: {}", pagos.getCompraID());
        Pagos nuevoPago = servicio.guardarPago(pagos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPago);
        
    }

    @PutMapping("/{pagoID}")
    public ResponseEntity<Pagos> actualizarPago(@PathVariable Long pagoID, @Valid @RequestBody Pagos pagos){
        log.info("Actualizando pago ID: {}", pagoID);
        Pagos pagoExistente = servicio.obtenerPagoPorId(pagoID);
        if (pagoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        pagoExistente.setPagoID(pagoID);
        pagoExistente.setCompraID(pagos.getCompraID());
        pagoExistente.setMonto_total(pagos.getMonto_total());
        pagoExistente.setMetodo_pago(pagos.getMetodo_pago());
        pagoExistente.setEstado_pago(pagos.getEstado_pago());

        Pagos pagoActualizado = servicio.guardarPago(pagoExistente);
        return ResponseEntity.ok(pagoActualizado);
    }

    @DeleteMapping("/{pagoID}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Long pagoID){
        log.info("Eliminando pago ID: {}", pagoID);
        servicio.eliminarPago(pagoID);
        return ResponseEntity.noContent().build();
        
    }
}

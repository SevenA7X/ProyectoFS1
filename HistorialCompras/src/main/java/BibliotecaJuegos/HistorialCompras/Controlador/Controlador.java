package BibliotecaJuegos.HistorialCompras.Controlador;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;
import BibliotecaJuegos.HistorialCompras.Servicio.Servicio;
import jakarta.validation.Valid;



@RestController
@RequestMapping("api/v1/historialcompras")
public class Controlador {

    @Autowired
    private Servicio servicio;

    @GetMapping
    public ResponseEntity<List<HistorialCompras>> listarHistorialCompras(){
        List<HistorialCompras> listaHistorialCompras = servicio.findAll();
        if (listaHistorialCompras.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaHistorialCompras);
        }
    }

    @GetMapping("/{historialID}")
    public ResponseEntity<HistorialCompras> obtenerHistorialComprasPorId(@Valid @PathVariable Long historialID){
        HistorialCompras historialCompras = servicio.findById(historialID);
        if (historialCompras != null) {
            return ResponseEntity.ok(historialCompras);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<HistorialCompras> agregarHistorialCompras(@RequestBody HistorialCompras historialCompras){
        try {
            HistorialCompras nuevoHistorialCompras = servicio.save(historialCompras);
            return ResponseEntity.ok(nuevoHistorialCompras);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{historialID}")
    public ResponseEntity<HistorialCompras> modificarHistorialCompras(@PathVariable Long historialID, @Valid @RequestBody HistorialCompras historialCompras){
        try {
            HistorialCompras historialComprasExistente = servicio.findById(historialID);
            if (historialComprasExistente != null) {
                historialComprasExistente.setCompraID(historialID);
                historialComprasExistente.setUsuarioID(historialCompras.getUsuarioID());
                historialComprasExistente.setFechaCompra(historialCompras.getFechaCompra());
                historialComprasExistente.setMontoTotal(historialCompras.getMontoTotal());
                historialComprasExistente.setEstadoPago(historialCompras.getEstadoPago());
                HistorialCompras historialComprasActualizado = servicio.save(historialComprasExistente);
                return ResponseEntity.ok(historialComprasActualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();

        }   
    }
}

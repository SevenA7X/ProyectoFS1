package BibliotecaDigital.Compras.Controlador;

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

import BibliotecaDigital.Compras.Modelo.Compra;
import BibliotecaDigital.Compras.Servicio.Servicio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/compras")
public class Controlador {
    @Autowired
    private Servicio servicio;

    @GetMapping
    public ResponseEntity<List<Compra>> listarCompras(){
        List<Compra> listaCompras = servicio.findAll();
        if (listaCompras.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaCompras);
        }
    }

    @GetMapping("/{compraID}")
    public ResponseEntity<Compra> buscarCompra(@Valid @PathVariable Long compraID){
        try {
            Compra com = servicio.findById(compraID);
            return ResponseEntity.ok(com);
            
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Compra> agregarCompra(@RequestBody Compra compra){
        try {
            Compra com = servicio.save(compra);
            return ResponseEntity.status(HttpStatus.CREATED).body(com);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{compraID}")
    public ResponseEntity<Compra> modificarCompra(@PathVariable Long compraID, @Valid @RequestBody Compra compra){
        try {
            Compra com = servicio.findById(compraID);
            if (com == null) {
                return ResponseEntity.notFound().build();
            }

            com.setCompraID(compraID);
            com.setUsuarioID(com.getUsuarioID());
            com.setVideojuegoID(com.getVideojuegoID());
            com.setFecha_compra(com.getFecha_compra());
            com.setEstado_orden(com.getEstado_orden());
            servicio.save(com);
            return ResponseEntity.ok(com);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{compraID}")
    public ResponseEntity<Compra> eliminarCompra(@PathVariable Long compraID){
        try {
            Compra com = servicio.findById(compraID);
            if (com == null) {
                return ResponseEntity.notFound().build();
            }
            servicio.delete(compraID);
            return ResponseEntity.ok(com);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

package BibliotecaJuegos.HistorialCompras.Controlador;

import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;
import BibliotecaJuegos.HistorialCompras.Repositorio.Repositorio;
import BibliotecaJuegos.HistorialCompras.client.ComprasFeignClient;
import BibliotecaJuegos.HistorialCompras.dto.CompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historial")
public class Controlador {

    @Autowired
    private Repositorio historialRepository;

    @Autowired
    private ComprasFeignClient comprasFeignClient;

    @GetMapping
    public ResponseEntity<List<HistorialCompras>> obtenerTodoElHistorial() {
        List<HistorialCompras> historial = historialRepository.findAll();
        return ResponseEntity.ok(historial);
    }


    @GetMapping("/usuario/{usuarioID}")
    public ResponseEntity<List<HistorialCompras>> obtenerHistorialPorUsuario(@PathVariable Long usuarioID) {
        List<HistorialCompras> historial = historialRepository.findByUsuarioID(usuarioID);
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/usuario/{usuarioID}/total")
    public ResponseEntity<Double> obtenerTotalGastado(@PathVariable Long usuarioID) {
        Double total = historialRepository.calcularTotalGastadoPorUsuario(usuarioID);
        return ResponseEntity.ok(total != null ? total : 0.0);
    }

    @GetMapping("/remoto/compras")
    public ResponseEntity<List<CompraDTO>> obtenerComprasRemotas() {
        List<CompraDTO> comprasRemotas = comprasFeignClient.obtenerTodasLasCompras();
        return ResponseEntity.ok(comprasRemotas);
    }
}
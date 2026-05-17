package BibliotecaJuegos.HistorialCompras.Controlador;

import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;
import BibliotecaJuegos.HistorialCompras.Repositorio.Repositorio;
import BibliotecaJuegos.HistorialCompras.Servicio.Servicio;
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

    @Autowired
    private Servicio servicio;

    @GetMapping
    public ResponseEntity<List<HistorialCompras>> obtenerTodoElHistorial() {
        List<HistorialCompras> historial = historialRepository.findAll();
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/usuario/{usuarioID}")
    public ResponseEntity<List<HistorialCompras>> obtenerHistorialPorUsuario(@PathVariable Long usuarioID) {
        List<HistorialCompras> historial = servicio.obtenerHistorialPorUsuario(usuarioID);
        
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/remoto/compras")
    public ResponseEntity<List<HistorialCompras>> guardarYObtenerComprasRemotas() {
        List<CompraDTO> comprasRemotas = comprasFeignClient.obtenerTodasLasCompras();
        
        java.util.ArrayList<HistorialCompras> historialGuardado = new java.util.ArrayList<>();

        for (CompraDTO compraDto : comprasRemotas) {
            HistorialCompras historial = new HistorialCompras();
            
            historial.setCompraID(compraDto.getId());
            historial.setUsuarioID(compraDto.getUsuarioId());
            if (compraDto.getFechaCompra() != null) {
                java.time.LocalDate fechaParseada = java.time.LocalDate.parse(compraDto.getFechaCompra());
                historial.setFecha_compra(fechaParseada);
            }
            historial.setEstado_pago("Compra sincronizada desde el microservicio remoto. Estado: " + compraDto.getEstado());

            HistorialCompras registroGuardado = historialRepository.save(historial);
            historialGuardado.add(registroGuardado);
        }

        return ResponseEntity.ok(historialGuardado);
        }
    }
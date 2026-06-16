package BibliotecaJuegos.HistorialCompras.Controlador;

import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;
import BibliotecaJuegos.HistorialCompras.Repositorio.Repositorio;
import BibliotecaJuegos.HistorialCompras.Servicio.Servicio;
import BibliotecaJuegos.HistorialCompras.client.ComprasFeignClient;
import BibliotecaJuegos.HistorialCompras.dto.CompraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/v1/historial")
@Tag(name = "Historial de Compras", description = "Operaciones relacionadas con el historial y sincronización de compras")
public class Controlador {

    @Autowired
    private Repositorio historialRepository;

    @Autowired
    private ComprasFeignClient comprasFeignClient;

    @Autowired
    private Servicio servicio;

    @GetMapping
    @Operation(summary = "Obtener todo el historial", description = "Obtiene una lista global de todos los historiales de compra almacenados localmente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "204", description = "No hay registros en el historial")
    })
    public ResponseEntity<List<HistorialCompras>> obtenerTodoElHistorial() {
        List<HistorialCompras> historial = historialRepository.findAll();
        if (historial.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/usuario/{usuarioID}")
    @Operation(summary = "Obtener historial por Usuario", description = "Obtiene la lista de compras históricas asociadas a un ID de usuario específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación Exitosa"),
        @ApiResponse(responseCode = "204", description = "El usuario no registra compras en su historial"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<List<HistorialCompras>> obtenerHistorialPorUsuario(@PathVariable Long usuarioID) {
        List<HistorialCompras> historial = servicio.obtenerHistorialPorUsuario(usuarioID);
        if (historial.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/remoto/compras")
    @Operation(
        summary = "Sincronizar y obtener compras remotas", 
        description = "Conecta con el microservicio de Compras vía Feign, descarga los registros, los procesa, los almacena localmente y devuelve el historial guardado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sincronización exitosa y registros almacenados"),
        @ApiResponse(responseCode = "204", description = "No se encontraron compras remotas para sincronizar"),
        @ApiResponse(responseCode = "500", description = "Fallo en la comunicación con el microservicio remoto de Compras")
    })
    public ResponseEntity<List<HistorialCompras>> guardarYObtenerComprasRemotas() {
        List<CompraDTO> comprasRemotas = comprasFeignClient.obtenerTodasLasCompras();
        
        if (comprasRemotas == null || comprasRemotas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
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
package BibliotecaJuegos.HistorialCompras.client;

import BibliotecaJuegos.HistorialCompras.dto.CompraDTO;
import org.springframework.cloud.openfeign.FeignClient; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@FeignClient(
    name = "microservicio-compras", 
    url = "http://localhost:8085" 
)
public interface ComprasFeignClient {

    @GetMapping("/api/v1/compras")
    List<CompraDTO> obtenerTodasLasCompras();

    @GetMapping("/api/v1/compras/usuario/{usuarioId}")
    List<CompraDTO> obtenerComprasPorUsuario(@PathVariable("usuarioId") Long usuarioId);
}
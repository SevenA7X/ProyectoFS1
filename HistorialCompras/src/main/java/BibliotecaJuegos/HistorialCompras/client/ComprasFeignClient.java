package BibliotecaJuegos.HistorialCompras.client;


import BibliotecaJuegos.HistorialCompras.dto.CompraDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface ComprasFeignClient {

    @RequestLine("GET /api/compras")
    @Headers("Accept: application/json")
    List<CompraDTO> obtenerTodasLasCompras();

    @RequestLine("GET /api/compras/usuario/{usuarioId}")
    @Headers("Accept: application/json")
    List<CompraDTO> obtenerComprasPorUsuario(@Param("usuarioId") String usuarioId);
}
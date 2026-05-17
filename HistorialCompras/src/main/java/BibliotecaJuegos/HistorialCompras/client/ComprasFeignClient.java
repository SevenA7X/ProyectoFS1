package BibliotecaJuegos.HistorialCompras.client;


import BibliotecaJuegos.HistorialCompras.dto.CompraDTO;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import java.util.List;

public interface ComprasFeignClient {

    @RequestLine("GET /api/v1/compras")
    @Headers("Accept: application/json")
    List<CompraDTO> obtenerTodasLasCompras();

    @RequestLine("GET /api/v1/compras/usuario/{usuarioId}")
    @Headers("Accept: application/json")
    List<CompraDTO> obtenerComprasPorUsuario(@Param("usuarioId") Long usuarioId);
}
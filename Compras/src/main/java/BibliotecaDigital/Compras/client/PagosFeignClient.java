package BibliotecaDigital.Compras.client;

import BibliotecaDigital.Compras.dto.PagosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "pagos-ms", url = "http://localhost:8084/api/v1/pagos")
public interface PagosFeignClient {
    @PostMapping
    ResponseEntity<PagosDTO> crearPago(PagosDTO pagosDTO);
}

package BibliotecaJuegos.LicenciasYDescargas.client;

import BibliotecaJuegos.LicenciasYDescargas.dto.ComprasDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "compras-ms", url = "http://localhost:8085/api/v1/compras")
public interface ComprasFeignClient {

    @GetMapping("/{id}")
    ComprasDTO obtenerCompraPorId(@PathVariable("id") Long id);
}
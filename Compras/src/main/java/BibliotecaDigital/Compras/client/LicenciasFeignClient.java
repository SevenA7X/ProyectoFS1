package BibliotecaDigital.Compras.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import BibliotecaDigital.Compras.dto.ComprasDTO;


@FeignClient(name = "ms-licencias", url = "http://localhost:8087") 
public interface LicenciasFeignClient {

    @PostMapping("/api/v1/licencias/generar")
    void generarLicenciaPorCompra(@RequestBody ComprasDTO compraDTO);
}
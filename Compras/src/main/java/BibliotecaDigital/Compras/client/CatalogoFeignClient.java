package BibliotecaDigital.Compras.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import BibliotecaDigital.Compras.dto.JuegoValidacionDTO; // <-- Aquí estaba el error de importación

@FeignClient(name = "microservicio-catalogo", url = "localhost:8081/api/v1/juegos")
public interface CatalogoFeignClient {

    @GetMapping("/{id}")
    JuegoValidacionDTO obtenerJuegoPorId(@PathVariable("id") Long id);
}
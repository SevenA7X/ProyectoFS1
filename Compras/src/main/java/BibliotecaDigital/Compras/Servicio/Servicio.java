package BibliotecaDigital.Compras.Servicio;

import BibliotecaDigital.Compras.dto.ComprasDTO;
import BibliotecaDigital.Compras.Modelo.Compra;
import BibliotecaDigital.Compras.Repositorio.Repositorio;

// Importaciones necesarias para la comunicación con Catálogo
import BibliotecaDigital.Compras.client.CatalogoFeignClient; 
import BibliotecaDigital.Compras.dto.JuegoValidacionDTO; 
import feign.FeignException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j 
@Service
@Transactional
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    // Inyección del cliente Feign para conectarse con Catálogo
    @Autowired
    private CatalogoFeignClient catalogoFeignClient;

    public List<ComprasDTO> findAll() {
        log.info("Capa Servicio Compras: Recuperando todos los registros");
        List<Compra> entidades = repositorio.findAll();
        List<ComprasDTO> dtos = new ArrayList<>();

        for (Compra compra : entidades) {
            dtos.add(convertirADTO(compra));
        }
        return dtos;
    }

    public ComprasDTO findById(Long compraID) {
        log.info("Capa Servicio Compras: Buscando compra con ID {}", compraID);
        Optional<Compra> oCompra = repositorio.findById(compraID);

        if (oCompra.isPresent()) {
            return convertirADTO(oCompra.get());
        } else {
            log.error("Capa Servicio Compras: No se encontró el ID {}", compraID);
            throw new RuntimeException("Compra no encontrada");
        }
    }

    public ComprasDTO save(ComprasDTO comprasDTO) {
        log.info("Capa Servicio Compras: Validando videojuego con ID {} en MS Catálogo antes de procesar la compra", comprasDTO.getVideojuegoID());
        
        // --- INICIO DE LA COMUNICACIÓN CON CATÁLOGO ---
        try {
            // Llamada síncrona al microservicio de Catálogo
            JuegoValidacionDTO juego = catalogoFeignClient.obtenerJuegoPorId(comprasDTO.getVideojuegoID());
            log.info("Validación exitosa: El videojuego '{}' existe en el catálogo y está disponible.", juego.getTitulo());

        } catch (FeignException.NotFound e) {
            log.error("Error de validación: El videojuego con ID {} no existe.", comprasDTO.getVideojuegoID());
            throw new IllegalArgumentException("No se puede procesar la compra: El videojuego no existe en el catálogo.");
        } catch (FeignException e) {
            log.error("Error de comunicación con MS Catálogo: {}", e.getMessage());
            throw new RuntimeException("Error de comunicación con el servicio de Catálogo. Intente más tarde.");
        }
        // --- FIN DE LA COMUNICACIÓN ---

        log.info("Capa Servicio Compras: Guardando nueva orden de compra para el usuario {}", comprasDTO.getUsuarioID());
        Compra entidad = convertirAEntidad(comprasDTO);
        Compra guardada = repositorio.save(entidad);
        
        return convertirADTO(guardada);
    }

    public void delete(Long compraID) {
        log.info("Capa Servicio Compras: Eliminando compra con ID {}", compraID);
        if (repositorio.existsById(compraID)) {
            repositorio.deleteById(compraID);
            log.info("Capa Servicio Compras: Compra eliminada correctamente.");
        } else {
            log.warn("Fallo al eliminar: No existe compra con ID {}", compraID);
            throw new RuntimeException("No se puede eliminar: El ID no existe");
        }
    }

    private ComprasDTO convertirADTO(Compra entidad) {
        ComprasDTO dto = new ComprasDTO();
        dto.setCompraID(entidad.getCompraID());
        dto.setUsuarioID(entidad.getUsuarioID());
        dto.setVideojuegoID(entidad.getVideojuegoID());
        dto.setFecha_compra(entidad.getFecha_compra());
        dto.setEstado_orden(entidad.getEstado_orden());
        return dto;
    }

    private Compra convertirAEntidad(ComprasDTO dto) {
        Compra entidad = new Compra();
        entidad.setCompraID(dto.getCompraID());
        entidad.setUsuarioID(dto.getUsuarioID());
        entidad.setVideojuegoID(dto.getVideojuegoID());
        entidad.setFecha_compra(dto.getFecha_compra());
        entidad.setEstado_orden(dto.getEstado_orden());
        return entidad;
    }
}
package BibliotecaDigital.Compras.Servicio;

import BibliotecaDigital.Compras.dto.ComprasDTO;
import BibliotecaDigital.Compras.Modelo.Compra;
import BibliotecaDigital.Compras.Repositorio.Repositorio;


import BibliotecaDigital.Compras.client.UsuarioFeingClient;
import BibliotecaDigital.Compras.client.CatalogoFeignClient; 
import BibliotecaDigital.Compras.dto.JuegoValidacionDTO;
import BibliotecaDigital.Compras.dto.UsuarioDTO;
import feign.FeignException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Autowired
    private CatalogoFeignClient catalogoFeignClient;

    @Autowired
    private UsuarioFeingClient usuarioFeignClient;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra no encontrada");
        }
    }

public ComprasDTO save(ComprasDTO comprasDTO) {

        log.info("Capa Servicio Compras: Validando usuario con ID {} en MS Usuarios antes de procesar la compra", comprasDTO.getUsuarioID());
        try {
            // Llamada síncrona al microservicio de Usuarios
            UsuarioDTO usuario = usuarioFeignClient.obtenerUsuarioPorId(comprasDTO.getUsuarioID());
            log.info("Validación exitosa: El usuario '{}' existe y tiene el rol {}.", usuario.getNombreUsuario(), usuario.getRol());

            // Validación opcional de regla de negocio basada en el DTO real
            if ("MODERADOR".equals(usuario.getRol())) {
                log.warn("Error de validación: El usuario con ID {} es MODERADOR y no puede comprar.", comprasDTO.getUsuarioID());
                throw new IllegalArgumentException("No se puede procesar la compra: Los moderadores no están autorizados a comprar.");
            }

        } catch (FeignException.NotFound e) {
            if (e.status() == 404 || e.status() == 409) {
            log.error("Error de validación: El usuario con ID {} no existe.", comprasDTO.getUsuarioID());
            throw new IllegalArgumentException("No se puede procesar la compra: El usuario comprador no existe.");
            }
        } catch (FeignException e) {
            log.error("Error de comunicación con MS Usuarios: {}", e.getMessage());
            throw new RuntimeException("Error de comunicación con el servicio de Usuarios. Intente más tarde.");
        }



        log.info("Capa Servicio Compras: Validando videojuego con ID {} en MS Catálogo antes de procesar la compra", comprasDTO.getVideojuegoID());
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
package BibliotecaJuegos.HistorialCompras.Servicio;

import BibliotecaJuegos.HistorialCompras.dto.HistorialComprasDTO;
import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;
import BibliotecaJuegos.HistorialCompras.Repositorio.Repositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class Servicio {

    @Autowired
    private Repositorio repositorio;

public List<HistorialComprasDTO> listarTodo() {
        log.info("Capa Servicio Historial: Recuperando todos los registros");
        List<HistorialCompras> entidades = repositorio.findAll();

        if (entidades.isEmpty()) {
            log.warn("Capa Servicio Historial: No se encontraron registros en la base de datos. Lanzando 404.");
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, 
                "No existen registros en el historial de compras"
            );
        }

        List<HistorialComprasDTO> dtos = new ArrayList<>();
        for (HistorialCompras entidad : entidades) {
            dtos.add(convertirADTO(entidad));
        }
        
        log.info("Capa Servicio Historial: Transformados {} registros", dtos.size());
        return dtos;
    }
    public List<HistorialCompras> obtenerHistorialPorUsuario(Long usuarioID) {
        log.info("Capa Servicio Historial: Buscando compras locales para el usuario ID {}", usuarioID);
        
        List<HistorialCompras> historial = repositorio.findByUsuarioID(usuarioID);
        
        if (historial == null || historial.isEmpty()) {
            log.warn("Capa Servicio Historial: El usuario ID {} no tiene compras locales.", usuarioID);
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, 
                "El usuario no tiene compras registradas"
            );
        }

        return historial;
    }

    public HistorialComprasDTO guardar(HistorialComprasDTO dto) {
        log.info("Capa Servicio Historial: Registrando nuevo historial para compra ID {}", dto.getCompraID());
        HistorialCompras entidad = convertirAEntidad(dto);
        HistorialCompras guardada = repositorio.save(entidad);
        return convertirADTO(guardada);
    }

    public void eliminar(Long id) {
        log.info("Capa Servicio Historial: Eliminando registro ID {}", id);
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar: ID no existe");
        }
    }

    private HistorialComprasDTO convertirADTO(HistorialCompras entidad) {
        HistorialComprasDTO dto = new HistorialComprasDTO();
        dto.setHistorialID(entidad.getHistorialID());
        dto.setCompraID(entidad.getCompraID());
        dto.setUsuarioID(entidad.getUsuarioID());
        dto.setFecha_compra(entidad.getFecha_compra());
        dto.setEstado_pago(entidad.getEstado_pago());
        return dto;
    }

    private HistorialCompras convertirAEntidad(HistorialComprasDTO dto) {
        HistorialCompras entidad = new HistorialCompras();
        entidad.setHistorialID(dto.getHistorialID());
        entidad.setCompraID(dto.getCompraID());
        entidad.setUsuarioID(dto.getUsuarioID());
        entidad.setFecha_compra(dto.getFecha_compra());
        entidad.setEstado_pago(dto.getEstado_pago());
        return entidad;
    }
}
package BibliotecaJuegos.HistorialCompras.Servicio;

import BibliotecaJuegos.HistorialCompras.dto.HistorialComprasDTO;
import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;
import BibliotecaJuegos.HistorialCompras.Repositorio.Repositorio;
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

    public List<HistorialComprasDTO> listarTodo() {
        log.info("Capa Servicio Historial: Recuperando todos los registros");
        List<HistorialCompras> entidades = repositorio.findAll();
        List<HistorialComprasDTO> dtos = new ArrayList<>();

        for (HistorialCompras entidad : entidades) {
            dtos.add(convertirADTO(entidad));
        }
        log.info("Capa Servicio Historial: Transformados {} registros", dtos.size());
        return dtos;
    }

    public HistorialComprasDTO buscarPorId(Long id) {
        log.info("Capa Servicio Historial: Buscando registro con ID {}", id);
        Optional<HistorialCompras> oHistorial = repositorio.findById(id);

        if (oHistorial.isPresent()) {
            return convertirADTO(oHistorial.get());
        } else {
            log.error("Capa Servicio Historial: ID {} no encontrado", id);
            throw new RuntimeException("Entrada de historial no encontrada");
        }
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
        dto.setMonto_total(entidad.getMonto_total());
        dto.setEstado_pago(entidad.getEstado_pago());
        return dto;
    }

    private HistorialCompras convertirAEntidad(HistorialComprasDTO dto) {
        HistorialCompras entidad = new HistorialCompras();
        entidad.setHistorialID(dto.getHistorialID());
        entidad.setCompraID(dto.getCompraID());
        entidad.setUsuarioID(dto.getUsuarioID());
        entidad.setFecha_compra(dto.getFecha_compra());
        entidad.setMonto_total(dto.getMonto_total());
        entidad.setEstado_pago(dto.getEstado_pago());
        return entidad;
    }
}
package BibliotecaDigital.Pagos.Servicio;

import BibliotecaDigital.Pagos.dto.PagosDTO;
import BibliotecaDigital.Pagos.Modelo.Pagos;
import BibliotecaDigital.Pagos.Repositorio.Repositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class Servicio {

    @Autowired
    private Repositorio repositorio;

    public List<PagosDTO> listarPagos() {
        log.info("Capa Servicio: Recuperando todos los registros");
        List<Pagos> listaEntidades = repositorio.findAll();
        List<PagosDTO> listaDTOs = new ArrayList<>();

        for (Pagos pago : listaEntidades) {
            listaDTOs.add(convertirADTO(pago));
        }
        log.info("Capa Servicio: Se transformaron {} registros a DTO", listaDTOs.size());
        return listaDTOs;
    }

    public PagosDTO obtenerPagoPorId(Long pagoID) {
        log.info("Capa Servicio: Buscando pago con ID {}", pagoID);
        // Nota: En JpaRepository el método estándar es findById
        Optional<Pagos> oPago = repositorio.findById(pagoID);

        if (oPago.isPresent()) {
            return convertirADTO(oPago.get());
        } else {
            log.error("Capa Servicio: ID {} no encontrado", pagoID);
            throw new RuntimeException("Pago no encontrado");
        }
    }

    public PagosDTO guardarPago(PagosDTO pagoDTO) {
        log.info("Capa Servicio: Registrando nuevo pago para compra ID {}", pagoDTO.getCompraID());
        
        Pagos entidadNueva = convertirAEntidad(pagoDTO);
        Pagos entidadGuardada = repositorio.save(entidadNueva);
        
        log.info("Capa Servicio: Pago guardado con éxito. ID asignado: {}", entidadGuardada.getPagoID());
        return convertirADTO(entidadGuardada);
    }

    public PagosDTO actualizarPago(Long pagoID, PagosDTO pagoDTO) {
        log.info("Capa Servicio: Actualizando ID {}", pagoID);
        Optional<Pagos> oPagoExistente = repositorio.findById(pagoID);

        if (oPagoExistente.isPresent()) {
            Pagos pago = oPagoExistente.get();
            
            pago.setCompraID(pagoDTO.getCompraID());
            pago.setMonto_total(pagoDTO.getMonto_total());
            pago.setMetodo_pago(pagoDTO.getMetodo_pago());
            pago.setEstado_pago(pagoDTO.getEstado_pago());
            pago.setFecha(pagoDTO.getFecha());

            Pagos actualizado = repositorio.save(pago);
            return convertirADTO(actualizado);
        } else {
            throw new RuntimeException("No se puede actualizar: El registro no existe");
        }
    }

    public void eliminarPago(Long pagoID) {
        log.info("Capa Servicio: Intentando eliminar pago con ID {}", pagoID);
        if (repositorio.existsById(pagoID)) {
            repositorio.deleteById(pagoID);
            log.info("Capa Servicio: Pago ID {} eliminado", pagoID);
        } else {
            log.error("Capa Servicio: No se pudo eliminar, ID {} no existe", pagoID);
            throw new RuntimeException("Error al eliminar: Pago no encontrado");
        }
    }

    private PagosDTO convertirADTO(Pagos pago) {
        PagosDTO dto = new PagosDTO();
        dto.setPagoID(pago.getPagoID());
        dto.setCompraID(pago.getCompraID());
        dto.setMonto_total(pago.getMonto_total());
        dto.setMetodo_pago(pago.getMetodo_pago());
        dto.setEstado_pago(pago.getEstado_pago());
        dto.setFecha(pago.getFecha());
        return dto;
    }

    private Pagos convertirAEntidad(PagosDTO dto) {
        Pagos entidad = new Pagos();
        entidad.setPagoID(dto.getPagoID());
        entidad.setCompraID(dto.getCompraID());
        entidad.setMonto_total(dto.getMonto_total());
        entidad.setMetodo_pago(dto.getMetodo_pago());
        entidad.setEstado_pago(dto.getEstado_pago());
        entidad.setFecha(dto.getFecha());
        return entidad;
    }
}
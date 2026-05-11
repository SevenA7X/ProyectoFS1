package BibliotecaDigital.Compras.Servicio;

import BibliotecaDigital.Compras.dto.ComprasDTO;
import BibliotecaDigital.Compras.Modelo.Compra;
import BibliotecaDigital.Compras.Repositorio.Repositorio;
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

    public ComprasDTO save(ComprasDTO ComprasDTO) {
        log.info("Capa Servicio Compras: Guardando nueva compra para usuario {}", ComprasDTO.getUsuarioID());
        
        Compra entidad = convertirAEntidad(ComprasDTO);
        Compra guardada = repositorio.save(entidad);
        
        return convertirADTO(guardada);
    }

    public void delete(Long compraID) {
        log.info("Capa Servicio Compras: Eliminando compra con ID {}", compraID);
        if (repositorio.existsById(compraID)) {
            repositorio.deleteById(compraID);
        } else {
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
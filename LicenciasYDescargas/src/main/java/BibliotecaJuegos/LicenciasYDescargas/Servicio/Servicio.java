package BibliotecaJuegos.LicenciasYDescargas.Servicio;

import BibliotecaJuegos.LicenciasYDescargas.dto.LicenciasYDescargasDTO;
import BibliotecaJuegos.LicenciasYDescargas.dto.ComprasDTO;
import BibliotecaJuegos.LicenciasYDescargas.client.ComprasFeignClient; 
import BibliotecaJuegos.LicenciasYDescargas.Repositorio.Repositorio;
import BibliotecaJuegos.LicenciasYDescargas.Modelo.Licencia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // 👈 Importante para el 404
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException; // 👈 Importante para lanzar el error
import jakarta.transaction.Transactional;
import feign.FeignException;

import java.time.LocalDate;
import java.util.UUID;
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
    private ComprasFeignClient comprasClient; 

    public LicenciasYDescargasDTO emitirLicenciaPorCompra(LicenciasYDescargasDTO dto) {
        log.info("Capa Servicio Licencias: Generando licencia automática para la compra ID {}", dto);
        
        try {
            Licencia nuevaLicencia = new Licencia();
            nuevaLicencia.setUsuarioID(dto.getUsuarioID());
            nuevaLicencia.setVideojuegoID(dto.getVideojuegoID());
            nuevaLicencia.setFecha(LocalDate.now());
            nuevaLicencia.setCodigoLicencia(UUID.randomUUID().toString());
            Licencia guardada = repositorio.save(nuevaLicencia);
            log.info("¡Licencia generada automáticamente con éxito! ID: {}, Código: {}", guardada.getLicenciaID(), guardada.getCodigoLicencia());
            return convertirADTO(guardada);

        } catch (Exception e) {
            log.error("Error interno al guardar la licencia: {}", e.getMessage());
            throw new RuntimeException("No se pudo procesar la creación de la licencia.");
        }
    }

    public List<LicenciasYDescargasDTO> findAll() {
        log.info("Capa Servicio Licencias: Recuperando todos los registros");
        List<Licencia> entidades = repositorio.findAll();
        List<LicenciasYDescargasDTO> dtos = new ArrayList<>();
        for (Licencia l : entidades) {
            dtos.add(convertirADTO(l));
        }
        return dtos;
    }

    public LicenciasYDescargasDTO findById(Long id) {
        log.info("Capa Servicio Licencias: Buscando licencia con ID {}", id);
        Optional<Licencia> oLicencia = repositorio.findById(id);
        if (oLicencia.isPresent()) {
            return convertirADTO(oLicencia.get());
        } else {
            log.error("Capa Servicio Licencias: ID {} no encontrado", id);
            throw new RuntimeException("Licencia no encontrada");
        }
    }

    public List<LicenciasYDescargasDTO> obtenerLicenciasPorUsuario(Long usuarioId) {
        log.info("Capa Servicio Licencias: Buscando todas las licencias del usuario ID {}", usuarioId);
        
        List<Licencia> entidades = repositorio.findByUsuarioID(usuarioId);


        if (entidades.isEmpty()) {
            log.warn("Capa Servicio Licencias: El usuario ID {} no posee ninguna licencia. Lanzando 404.", usuarioId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no tiene licencias registradas");
        }

        List<LicenciasYDescargasDTO> dtos = new ArrayList<>();
        for (Licencia l : entidades) {
            dtos.add(convertirADTO(l));
        }
        return dtos;
    }

    public LicenciasYDescargasDTO save(LicenciasYDescargasDTO dto) {
        log.info("Capa Servicio Licencias: Guardando nueva licencia");
        Licencia entidad = convertirAEntidad(dto);
        return convertirADTO(repositorio.save(entidad));
    }

    public void delete(Long id) {
        log.info("Capa Servicio Licencias: Eliminando licencia ID {}", id);
        if (repositorio.existsById(id)) {
            repositorio.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar: ID no existe");
        }
    }

    private LicenciasYDescargasDTO convertirADTO(Licencia e) {
        LicenciasYDescargasDTO d = new LicenciasYDescargasDTO();
        d.setLicenciaID(e.getLicenciaID());
        d.setUsuarioID(e.getUsuarioID());
        d.setVideojuegoID(e.getVideojuegoID());
        d.setFecha(e.getFecha());
        d.setCodigoLicencia(e.getCodigoLicencia()); 
        return d;
    }

    private Licencia convertirAEntidad(LicenciasYDescargasDTO d) {
        Licencia e = new Licencia();
        e.setLicenciaID(d.getLicenciaID());
        e.setUsuarioID(d.getUsuarioID());
        e.setVideojuegoID(d.getVideojuegoID());
        e.setFecha(d.getFecha());
        e.setCodigoLicencia(d.getCodigoLicencia());
        return e;
    }
}
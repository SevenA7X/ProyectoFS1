package BibliotecaJuegos.LicenciasYDescargas.Servicio;

import BibliotecaJuegos.LicenciasYDescargas.dto.LicenciasYDescargasDTO;
import BibliotecaJuegos.LicenciasYDescargas.Modelo.Licencia;
import BibliotecaJuegos.LicenciasYDescargas.Repositorio.Repositorio;
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
        return d;
    }

    private Licencia convertirAEntidad(LicenciasYDescargasDTO d) {
        Licencia e = new Licencia();
        e.setLicenciaID(d.getLicenciaID());
        e.setUsuarioID(d.getUsuarioID());
        e.setVideojuegoID(d.getVideojuegoID());
        e.setFecha(d.getFecha());
        return e;
    }
}
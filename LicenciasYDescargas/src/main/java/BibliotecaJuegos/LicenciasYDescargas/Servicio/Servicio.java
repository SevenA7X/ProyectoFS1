package BibliotecaJuegos.LicenciasYDescargas.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BibliotecaJuegos.LicenciasYDescargas.Modelo.Licencia;
import BibliotecaJuegos.LicenciasYDescargas.Repositorio.Repositorio;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class Servicio {
    @Autowired    
    private Repositorio repositorio;

        public List<Licencia> findAll(){
            return repositorio.findAll();
        }

        public Licencia findById(Long licenciaID){
            return repositorio.findById(licenciaID).get();
        }

        public Licencia save(Licencia licencia){
            return repositorio.save(licencia);
        }

        public void delete(Long licenciaID){
            repositorio.deleteById(licenciaID);
        }
    }





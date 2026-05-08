package BibliotecaJuegos.LicenciasYDescargas.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BibliotecaJuegos.LicenciasYDescargas.Modelo.Licencia;

@Repository
public interface Repositorio extends JpaRepository<Licencia, Long>{
    
}

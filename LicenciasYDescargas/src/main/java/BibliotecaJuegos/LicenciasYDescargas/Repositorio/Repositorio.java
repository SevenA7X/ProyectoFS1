package BibliotecaJuegos.LicenciasYDescargas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BibliotecaJuegos.LicenciasYDescargas.modelo.Licencia;

@Repository
public interface Repositorio extends JpaRepository<Licencia, Long>{
    
}

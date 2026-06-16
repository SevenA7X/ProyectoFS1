package BibliotecaJuegos.LicenciasYDescargas.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BibliotecaJuegos.LicenciasYDescargas.Modelo.Licencia;

@Repository
public interface Repositorio extends JpaRepository<Licencia, Long>{
    List<Licencia> findByUsuarioID(Long usuarioID);
}

package BibliotecaDigital.Pagos.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BibliotecaDigital.Pagos.Modelo.Pagos;

@Repository
public interface Repositorio extends JpaRepository<Pagos, Long>{

    
}

package BibliotecaDigital.Pagos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BibliotecaDigital.Pagos.modelo.Pagos;

@Repository
public interface Repositorio extends JpaRepository<Pagos, Long>{

    
}

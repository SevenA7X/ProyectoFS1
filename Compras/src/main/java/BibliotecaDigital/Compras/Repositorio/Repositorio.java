package BibliotecaDigital.Compras.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BibliotecaDigital.Compras.modelo.Compra;

@Repository
public interface Repositorio extends JpaRepository<Compra, Long> {
    
}

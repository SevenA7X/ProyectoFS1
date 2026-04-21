package BibliotecaDigital.Compras.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BibliotecaDigital.Compras.Modelo.Compra;

@Repository
public interface Repositorio extends JpaRepository<Compra, Long> {
    
}

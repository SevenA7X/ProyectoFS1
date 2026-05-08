package BibliotecaJuegos.HistorialCompras.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BibliotecaJuegos.HistorialCompras.modelo.HistorialCompras;

@Repository
public interface Repositorio extends JpaRepository<HistorialCompras, Long>{

}

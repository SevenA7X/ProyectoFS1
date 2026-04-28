package BibliotecaJuegos.HistorialCompras.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;

@Repository
public interface Repositorio extends JpaRepository<HistorialCompras, Long>{

}

package BibliotecaJuegos.HistorialCompras.Repositorio;

import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Repositorio extends JpaRepository<HistorialCompras, Long> {
    List<HistorialCompras> findByUsuarioID(Long usuarioID);

    List<HistorialCompras> findByCompraID(Long compraID);
}
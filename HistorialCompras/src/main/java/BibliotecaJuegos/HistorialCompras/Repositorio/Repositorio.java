package BibliotecaJuegos.HistorialCompras.Repositorio;

import BibliotecaJuegos.HistorialCompras.Modelo.HistorialCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Repositorio extends JpaRepository<HistorialCompras, Long> {
    List<HistorialCompras> findByUsuarioID(Long usuarioID);

    List<HistorialCompras> findByCompraID(Long compraID);

    @Query("SELECT SUM(h.monto_total) FROM HistorialCompras h WHERE h.usuarioID = :usuarioID AND h.estado_pago = 'COMPRADO'")
    Double calcularTotalGastadoPorUsuario(@Param("usuarioID") Long usuarioID);
}
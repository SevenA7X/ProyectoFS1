package BibliotecaJuegos.HistorialCompras.Modelo;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "db_historialcompras")
@Entity
public class HistorialCompras {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long historialID;

    @Column(name = "compraID", nullable = false)
    private Long compraID;

    @Column(name = "usuarioID", nullable = false)
    private Long usuarioID;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fecha_compra;

    @Column(name = "monto_total", nullable = false)
    private Double monto_total;

    @Column(nullable = false)
    private String estado_pago;
}
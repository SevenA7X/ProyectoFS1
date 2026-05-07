package BibliotecaJuegos.HistorialCompras.Modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Long compraID;

    @Column(name = "usuarioID", nullable = false)
    @NotNull
    private Long usuarioID;

    @Column(name = "fecha_compra", nullable = false)
    @NotNull
    private LocalDate fecha_compra;


    @Column(name = "monto_total", nullable = false)
    @NotNull
    private Double monto_total;

    @NotBlank
    @Column(nullable=false)
    private String estado_pago;
}

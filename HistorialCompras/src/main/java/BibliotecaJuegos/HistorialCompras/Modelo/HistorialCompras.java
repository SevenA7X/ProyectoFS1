package BibliotecaJuegos.HistorialCompras.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotNull(message = "El ID de la compra es obligatorio")
    @Column(name = "compraID", nullable = false)
    private Long compraID;

    @NotNull(message = "El ID del usuario es obligatorio")
    @Column(name = "usuarioID", nullable = false)
    private Long usuarioID;

    @NotNull(message = "La fecha de compra es obligatoria")
    @PastOrPresent(message = "La fecha del historial no puede ser futura")
    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fecha_compra;

    @NotNull(message = "El monto total es obligatorio")
    @PositiveOrZero(message = "El monto total debe ser cero o un valor positivo")
    @Column(name = "monto_total", nullable = false)
    private Double monto_total;

    @NotBlank(message = "El estado del pago no puede estar vacío")
    @Column(nullable=false)
    private String estado_pago;
}


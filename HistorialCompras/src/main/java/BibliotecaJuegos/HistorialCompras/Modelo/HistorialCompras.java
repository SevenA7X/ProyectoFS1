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
@Table(name = "HISTORIALCOMPRAS")
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

    @Column(name = "fechaCompra", nullable = false)
    @NotNull
    private LocalDate fechaCompra;


    @Column(name = "montoTotal", nullable = false)
    @NotNull
    private Double montoTotal;

    @NotBlank
    @Column(nullable=false)
    private String estadoPago;
}

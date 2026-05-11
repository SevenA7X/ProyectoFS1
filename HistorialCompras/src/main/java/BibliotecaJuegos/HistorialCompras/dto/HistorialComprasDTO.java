package BibliotecaJuegos.HistorialCompras.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class HistorialComprasDTO {
    
    private Long historialID;

    @NotNull(message = "El ID de la compra es obligatorio")
    private Long compraID;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioID;

    @NotNull(message = "La fecha de compra es obligatoria")
    @PastOrPresent(message = "La fecha del historial no puede ser futura")
    private LocalDate fecha_compra;

    @NotNull(message = "El monto total es obligatorio")
    @PositiveOrZero(message = "El monto total debe ser cero o un valor positivo")
    private Double monto_total;

    @NotBlank(message = "El estado del pago no puede estar vacío")
    private String estado_pago;
}
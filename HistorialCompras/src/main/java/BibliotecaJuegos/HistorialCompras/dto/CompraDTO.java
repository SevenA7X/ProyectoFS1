package BibliotecaJuegos.HistorialCompras.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompraDTO {

    private Long id;

    @NotBlank(message = "El ID del usuario es obligatorio")
    private String usuarioId;

    @NotNull(message = "El ID del juego es obligatorio")
    private Long juegoId;

    private LocalDateTime fechaCompra;

    @NotNull(message = "El monto de la compra no puede ser nulo")
    @Positive(message = "El monto de la compra debe ser mayor a cero")
    private Double monto;
    
    @NotBlank(message = "El estado de la compra es obligatorio")
    private String estado;
}
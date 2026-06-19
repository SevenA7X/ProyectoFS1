package BibliotecaDigital.Compras.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ComprasDTO {
    private Long compraID;

    @NotNull(message = "El ID de usuario es obligatorio")
    private Long usuarioID;

    @NotNull(message = "El ID del videojuego es obligatorio")
    private Long videojuegoID;

    @NotNull(message = "La fecha de compra no puede ser nula")
    @PastOrPresent(message = "La fecha de compra no puede ser futura") 
    private LocalDate fecha_compra;

    @NotBlank(message = "El estado de la orden no puede estar vacío")
    private String estado_orden;
}
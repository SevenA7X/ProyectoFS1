package BibliotecaDigital.Compras.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PagosDTO {
    private Long pagoID;

    @NotNull(message = "El ID de la compra es obligatorio")
    private Long compraID;

    @Positive(message = "El monto debe ser un valor positivo")
    @NotNull(message = "El monto total no puede ser nulo")
    private Double monto_total;

    @NotBlank(message = "Debe especificar un método de pago")
    private String metodo_pago;

    @NotBlank(message = "El estado del pago es obligatorio")
    private String estado_pago;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;
}

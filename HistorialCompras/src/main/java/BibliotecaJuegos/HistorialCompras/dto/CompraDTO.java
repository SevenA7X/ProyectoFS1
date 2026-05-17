package BibliotecaJuegos.HistorialCompras.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class CompraDTO {

    @JsonAlias("compraID")
    private Long id;

    @NotNull(message = "El ID del usuario es obligatorio")
    @JsonAlias("usuarioID")
    private Long usuarioId;

    @NotNull(message = "El ID del juego es obligatorio")
    @JsonAlias("videojuegoID")
    private Long juegoId;

    @JsonAlias("fecha_compra")
    private String fechaCompra;

    @Positive(message = "El monto de la compra debe ser mayor a cero")
    private Double monto; 
    
    @NotBlank(message = "El estado de la compra es obligatorio")
    @JsonAlias("estado_orden")
    private String estado;
}
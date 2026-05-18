package BibliotecaJuegos.LicenciasYDescargas.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LicenciasYDescargasDTO {
    private Long licenciaID;

    @NotNull(message = "El ID de usuario es obligatorio")
    @Positive(message = "El ID de usuario debe ser un número positivo")
    private Long usuarioID;
    
    @NotNull(message = "El ID del videojuego es obligatorio")
    @Positive(message = "El ID del videojuego debe ser un número positivo")
    private Long videojuegoID;

    @NotNull(message = "La fecha de emisión de la licencia es obligatoria")
    @PastOrPresent(message = "La fecha de la licencia no puede ser una fecha futura")
    private LocalDate fecha;

    @NotBlank(message = "El código de licencia no puede estar vacío")
    private String codigoLicencia;
}
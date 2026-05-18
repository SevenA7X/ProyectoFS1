package com.proyectofs1.moderacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ModeracionDTO {
    
    private Long id;

    @NotNull(message = "El ID del contenido no puede ser nulo.")
    private Long contenidoId;

    @NotBlank(message = "El tipo de contenido no puede estar vacío.")
    @Size(max = 50, message = "El tipo de contenido no puede exceder los 50 caracteres.")
    private String tipoContenido;

    @NotBlank(message = "El resultado no puede estar vacío.")
    @Size(max = 20, message = "El resultado no puede exceder los 20 caracteres.")
    private String resultado;

    @Size(max = 250, message = "Las observaciones no pueden exceder los 250 caracteres.")
    private String observaciones;

    private LocalDateTime fechaRevision;
}
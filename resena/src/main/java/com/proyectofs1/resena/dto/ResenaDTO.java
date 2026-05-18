package com.proyectofs1.resena.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResenaDTO {
    
    private Long id;

    @NotNull(message = "El ID del usuario no puede ser nulo.")
    private Long usuarioId;

    @NotNull(message = "El ID del juego no puede ser nulo.")
    private Long juegoId;
    
    @NotBlank(message = "El comentario no puede estar vacío.")
    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres.")
    private String comentario;

    @NotNull(message = "La puntuación es obligatoria.")
    @Min(value = 1, message = "La puntuación mínima es 1.")
    @Max(value = 5, message = "La puntuación máxima es 5.")
    private Integer puntuacion;
    
    @NotBlank(message = "El estado no puede estar vacío.")
    private String estado;
}
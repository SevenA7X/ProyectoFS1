package com.proyectofs1.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class JuegoDTO {
    private Long id;
    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser un valor positivo")
    private Double precio;

    @NotBlank(message = "La categoría es obligatoria")
    private String nombreCategoria; // Solo el nombre, no la entidad completa
    
    // Si tu entidad "Juego" tiene más campos (como descripcion, genero, etc.), 
    // agrégalos aquí también.
}
package com.example.Notificaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificacionesDTO {
    private Long id;
    
    @NotNull(message = "El ID del usuario es requerido.")
    private Long usuarioId;
    
    @NotBlank(message = "El mensaje no puede estar vacío.")
    private String mensaje;
    
    @NotBlank(message = "El tipo de notificación es requerido.")
    private String tipo;
}


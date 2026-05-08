package com.example.Notificaciones.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDTO {

    @NotNull(message = "El Id del usuario no es válido") 
    private Long usuarioId;

    @NotBlank(message = "El mensaje no puede estar vacío") private String mensaje;
    private String tipo;

}

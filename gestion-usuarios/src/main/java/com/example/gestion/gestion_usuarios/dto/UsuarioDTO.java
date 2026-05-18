package com.example.gestion.gestion_usuarios.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreUsuario;

    @Email(message = "Debe ser un correo válido")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @Pattern(regexp = "^(ADMIN|CLIENTE|MODERADOR)$", message = "Rol no válido")
    private String rol;
}

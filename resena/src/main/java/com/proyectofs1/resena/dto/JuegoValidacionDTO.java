package com.proyectofs1.resena.dto;

import lombok.Data;

@Data
public class JuegoValidacionDTO {
    // Solo necesitamos saber el ID para confirmar que existe.
    // Si quisieras mostrar el título en el error, podrías agregarlo aquí.
    private Long id; 
}
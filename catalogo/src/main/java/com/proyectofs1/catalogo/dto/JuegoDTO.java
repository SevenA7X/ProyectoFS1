package com.proyectofs1.catalogo.dto;

import lombok.Data;

@Data
public class JuegoDTO {
    private Long id;
    private String titulo;
    private double precio;
    private String nombreCategoria; // Solo el nombre, no la entidad completa
    
    // Si tu entidad "Juego" tiene más campos (como descripcion, genero, etc.), 
    // agrégalos aquí también.
}
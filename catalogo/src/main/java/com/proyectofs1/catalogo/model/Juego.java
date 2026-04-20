package com.proyectofs1.catalogo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Table(name="Videojuego")
@NoArgsConstructor
@AllArgsConstructor
public class Juego {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "El título no puede exceder los 50 caracteres")
    @NotBlank(message = "El título no puede estar vacio.")
    @Column(nullable = false)
    private String titulo;

    @Size(max = 50, message = "El Genero no puede exceder los 50 caracteres")
    @NotBlank(message = "El Genero no puede estar vacio.")
    @Column(nullable = false)
    private String genero;
    
    @PositiveOrZero(message = "El precio debe ser positivo")
    @Column(nullable = false)
    private double precio;

    @Size(max = 50, message = "La descripcion no puede exceder los 250 caracteres")
    @NotBlank(message = "El Genero no puede estar vacio.")
    @Column(nullable = false)
    private String descripcion;
}
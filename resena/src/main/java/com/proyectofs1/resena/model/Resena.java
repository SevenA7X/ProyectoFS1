package com.proyectofs1.resena.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Resena")
@NoArgsConstructor
@AllArgsConstructor
public class Resena {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del usuario no puede ser nulo.")
    @Column(nullable = false)
    private Long usuarioId; // Referencia al microservicio de Usuarios

    @NotNull(message = "El ID del juego no puede ser nulo.")
    @Column(nullable = false)
    private Long juegoId; // Referencia al microservicio de Catálogo
    
    @NotBlank(message = "El comentario no puede estar vacío.")
    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres.")
    @Column(nullable = false, length = 500)
    private String comentario;

@NotNull(message = "La puntuación es obligatoria.")
    @Min(value = 1, message = "La puntuación mínima es 1.")
    @Max(value = 5, message = "La puntuación máxima es 5.")
    @Column(nullable = false)
    private Integer puntuacion; // Escala 1-5
    
    @NotBlank(message = "El estado no puede estar vacío.")
    @Column(nullable = false)
    private String estado; // Pendiente, Aprobado, Rechazado (para Moderación)
}

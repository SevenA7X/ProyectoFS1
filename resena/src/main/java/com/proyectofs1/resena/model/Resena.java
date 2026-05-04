package com.proyectofs1.resena.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Long juegoId;
    
    @Column(nullable = false, length = 500)
    private String comentario;

    @Column(nullable = false)
    private Integer puntuacion;
    
    @Column(nullable = false)
    private String estado;
}
package com.example.gestion.gestion_usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "La id ingresada no es válida, intente nuevamente")
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(unique=false)
    private String nombre;

    @NotBlank(message = "El rol no puede estar vacío")
    @Column(unique=false)
    private String rol;

    @NotBlank(message = "Las credenciales no pueden estar vacías")
    @Column(unique=false)
    private String credenciales;
    

}

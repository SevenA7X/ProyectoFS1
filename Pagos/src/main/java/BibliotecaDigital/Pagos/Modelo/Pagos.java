package BibliotecaDigital.Pagos.Modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pagoID;

    @NotNull
    @Column(nullable=false)
    private Long compraID;

    @Positive
    @NotNull
    @Column(nullable=false)
    private Double montoTotal;

    @NotBlank
    @Column(nullable=false)
    private String metodoPago;

    @NotBlank
    @Column(nullable=false)
    private String estadoPago;

    @NotNull
    @Column(nullable=false)
    private LocalDate fecha;
}

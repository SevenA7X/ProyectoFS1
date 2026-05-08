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

    @NotNull(message = "El ID de la compra es obligatorio")
    @Column(nullable=false)
    private Long compraID;

    @Positive(message = "El monto debe ser un valor positivo")
    @NotNull(message = "El monto total no puede ser nulo")
    @Column(nullable=false)
    private Double monto_total;

    @NotBlank(message = "Debe especificar un método de pago")
    @Column(nullable=false)
    private String metodo_pago;

    @NotBlank(message = "El estado del pago es obligatorio")
    @Column(nullable=false)
    private String estado_pago;

    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable=false)
    private LocalDate fecha;
}


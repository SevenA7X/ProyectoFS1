package com.proyectofs1.moderacion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="moderacion")
@NoArgsConstructor
@AllArgsConstructor
public class Moderacion {

    // Es el número de identificación único de cada registro de moderación en esta base de datos.
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // Guarda el ID del elemento que se está revisando (por ejemplo, el ID número 5 de una reseña). Como las bases de datos de los microservicios están separadas, esta es la forma de enlazarlos.

    @Column(nullable = false)
    private Long contenidoId;

    // Sirve para saber qué tipo de elemento se está revisando. Por ejemplo, aquí se escribiría "RESENA" o "JUEGO", para saber de qué microservicio viene el ID guardado arriba.
    
    
    @Column(nullable = false)
    private String tipoContenido;

    // Guarda la decisión final del sistema, como por ejemplo "APROBADO" o "RECHAZADO".
   
    @Column(nullable = false)
    private String resultado;

    // Un espacio para explicar por qué se tomó la decisión (por ejemplo: "Contiene palabras ofensivas"). Es opcional, ya que si se aprueba un texto, generalmente no hay nada extra que explicar.
    
    @Column(nullable = true)
    private String observaciones;

    // Guarda automáticamente la fecha y la hora exacta en la que se realizó esta revisión.
    @Column(nullable = false)
    private LocalDateTime fechaRevision = LocalDateTime.now();
}
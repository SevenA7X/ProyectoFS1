package com.example.Estadisticas_Uso.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eventos_estadisticas")
public class EventoEstadistica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private TipoEvento tipo;
    
    private Long usuarioId;
    private Long juegoId;
    private String juegoNombre;
    
    @Column(length = 1000)
    private String metadata;
    
    @Column(name = "fecha_evento")
    private LocalDateTime fechaEvento;
    
    private String ipAddress;
    
}

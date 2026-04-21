package com.example.Notificaciones.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class NotificacionModelo {

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long usuarioId;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false, length = 15)
    private String mensaje;
    
    @Enumerated(EnumType.STRING)
    private TipoNotificacion tipo;
    
    private boolean leida = false;
    
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;
    
    }

}

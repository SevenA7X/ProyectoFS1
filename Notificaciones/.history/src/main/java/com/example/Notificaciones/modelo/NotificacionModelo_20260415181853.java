package com.example.Notificaciones.modelo;
import javax.persistence.*;
import java.time.LocalDateTime;

public class NotificacionModelo {

    

@Entity
@Table(name = "notificaciones")
public class Notificacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long usuarioId;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false, length = 1000)
    private String mensaje;
    
    @Enumerated(EnumType.STRING)
    private TipoNotificacion tipo;
    
    private boolean leida = false;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;
    
}

}

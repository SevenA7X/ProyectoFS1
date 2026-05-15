package com.example.Notificaciones.repository;

import com.example.Notificaciones.modelo.NotificacionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionDTO, Long> {
    List<NotificacionDTO> findByUsuarioIdOrderByFechaCreacionDesc(Long usuarioId);
    List<NotificacionDTO> findByUsuarioIdAndLeidaFalse(Long usuarioId);
    long countByUsuarioIdAndLeidaFalse(Long usuarioId);
}

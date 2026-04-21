package com.example.Estadisticas_Uso.repository;

import com.biblioteca.estadistica.model.EventoEstadistica;
import com.biblioteca.estadistica.model.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface EventoEstadisticaRepository extends JpaRepository<EventoEstadistica, Long> {
    
    long countByTipoAndFechaEventoBetween(TipoEvento tipo, LocalDateTime inicio, LocalDateTime fin);
    
    long countByUsuarioIdAndTipoAndFechaEventoBetween(Long usuarioId, TipoEvento tipo, LocalDateTime inicio, LocalDateTime fin);
    
    @Query("SELECT e.juegoId, COUNT(e) FROM EventoEstadistica e WHERE e.tipo = :tipo AND e.fechaEvento BETWEEN :inicio AND :fin GROUP BY e.juegoId ORDER BY COUNT(e) DESC")
    List<Object[]> findTopJuegosByTipo(@Param("tipo") TipoEvento tipo, @Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
    
    @Query("SELECT FUNCTION('DATE', e.fechaEvento), COUNT(DISTINCT e.usuarioId) FROM EventoEstadistica e WHERE e.fechaEvento BETWEEN :inicio AND :fin GROUP BY FUNCTION('DATE', e.fechaEvento)")
    List<Object[]> findUsuariosActivosPorDia(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
    
    @Query("SELECT e.juegoId, e.juegoNombre, COUNT(e) as total FROM EventoEstadistica e WHERE e.tipo = 'COMPRA_REALIZADA' GROUP BY e.juegoId, e.juegoNombre ORDER BY total DESC")
    List<Object[]> findJuegosMasVendidos();
    
    @Query("SELECT COUNT(DISTINCT e.usuarioId) FROM EventoEstadistica e WHERE e.fechaEvento >= :fecha")
    long countUsuariosActivosDesde(@Param("fecha") LocalDateTime fecha);
}

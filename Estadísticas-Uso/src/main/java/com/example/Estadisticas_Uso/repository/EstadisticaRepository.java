package com.example.Estadisticas_Uso.repository;

import com.example.Estadisticas_Uso.modelo.Estadistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EstadisticaRepository extends JpaRepository<Estadistica, Long> {

    // Query Method simple (IE 2.1.2)
    long countByUsuarioId(Long usuarioId);

    // Consulta personalizada con JPQL (Ejemplo 2 de la rúbrica)
    @Query("SELECT e.accion, COUNT(e) FROM Estadistica e WHERE e.fecha BETWEEN :inicio AND :fin GROUP BY e.accion")
    List<Object[]> findResumenAccionesPorFechas(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // Obtener las acciones más frecuentes (Popularidad - Punto 5 del caso)
    @Query("SELECT e.accion, COUNT(e) as total FROM Estadistica e GROUP BY e.accion ORDER BY total DESC")
    List<Object[]> findAccionesMasPopulares();

    // Contar usuarios únicos que han interactuado desde una fecha (IE 2.3.2 para trazabilidad)
    @Query("SELECT COUNT(DISTINCT e.usuarioId) FROM Estadistica e WHERE e.fecha >= :fecha")
    long countUsuariosActivosDesde(@Param("fecha") LocalDateTime fecha);
}

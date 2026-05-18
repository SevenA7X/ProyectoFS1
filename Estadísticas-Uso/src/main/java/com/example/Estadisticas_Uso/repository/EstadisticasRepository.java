package com.example.Estadisticas_Uso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Estadisticas_Uso.modelo.Estadistica;

@Repository
public interface EstadisticasRepository extends JpaRepository<Estadistica, Long> {
}

package com.proyectofs1.resena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectofs1.resena.model.Resena; // Asegúrese de importar su modelo Resena

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
    

}
package com.proyectofs1.catalogo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.proyectofs1.catalogo.model.Juego;
@Repository

public interface JuegoRepository extends JpaRepository<Juego, Long> {
    List<Juego> findByGenero(String genero);
}
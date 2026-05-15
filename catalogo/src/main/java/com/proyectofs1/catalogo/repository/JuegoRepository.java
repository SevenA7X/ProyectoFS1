package com.proyectofs1.catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyectofs1.catalogo.model.Juego;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {
<<<<<<< HEAD
    // Búsqueda derivada existente
    List<Juego> findByGenero(String genero);

    // 1. Método Derivado: Búsqueda por coincidencia parcial en el título (Ignorando mayúsculas/minúsculas)
    // Útil para barras de búsqueda de texto libre.
    List<Juego> findByTituloContainingIgnoreCase(String titulo);

    // 2. Método Derivado: Búsqueda de juegos con un precio menor o igual al especificado
    // Útil para filtros de presupuesto.
    List<Juego> findByPrecioLessThanEqual(double precio);

    // 3. Consulta JPQL: Búsqueda combinada por género y precio máximo
    // Útil para cruzar múltiples filtros en la interfaz del cliente.
    @Query("SELECT j FROM Juego j WHERE j.genero = :genero AND j.precio <= :precioMaximo")
    List<Juego> buscarPorGeneroYPrecioMaximo(@Param("genero") String genero, @Param("precioMaximo") double precioMaximo);

    // Consulta SQL Nativa: Obtener los 3 juegos más económicos
=======
    
    // 1. Búsqueda derivada corregida: Ahora navega hasta el atributo "nombre" de la entidad "Categoria"
    List<Juego> findByCategoriaNombre(String nombre);

    // 2. Búsqueda por coincidencia parcial en el título (Se mantiene igual)
    List<Juego> findByTituloContainingIgnoreCase(String titulo);

    // 3. Búsqueda por precio menor o igual (Se mantiene igual)
    List<Juego> findByPrecioLessThanEqual(double precio);

    // 4. Consulta JPQL corregida: Se cambia "j.genero" por "j.categoria.nombre"
    @Query("SELECT j FROM Juego j WHERE j.categoria.nombre = :genero AND j.precio <= :precioMaximo")
    List<Juego> buscarPorGeneroYPrecioMaximo(@Param("genero") String genero, @Param("precioMaximo") double precioMaximo);

    // 5. Consulta SQL Nativa (Se mantiene igual ya que apunta a la tabla física)
>>>>>>> origin/develop
    @Query(value = "SELECT * FROM videojuego ORDER BY precio ASC LIMIT 3", nativeQuery = true)
    List<Juego> buscarTop3OfertasNativo();
}
package com.proyectofs1.moderacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectofs1.moderacion.model.Moderacion;

// La anotación @Repository le indica a Spring que este componente 
// es el encargado de interactuar directamente con la base de datos.
@Repository

// 1. Debe ser una "interface" y no una clase, porque Spring Data JPA 
//    se encarga de generar el código de las consultas por detrás automáticamente.
// 2. Al usar "extends JpaRepository", esta interfaz hereda inmediatamente 
//    métodos listos para usar, como guardar (save), buscar (findById) o borrar (deleteById).
// 3. Los parámetros <Moderacion, Long> le especifican a Spring que este repositorio 
//    guardará objetos del tipo "Moderacion" y que el @Id de esa clase es de tipo "Long".
public interface ModeracionRepository extends JpaRepository<Moderacion, Long> {

}

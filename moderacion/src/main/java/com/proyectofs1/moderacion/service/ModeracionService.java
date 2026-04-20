package com.proyectofs1.moderacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.proyectofs1.moderacion.model.Moderacion;
import com.proyectofs1.moderacion.repository.ModeracionRepository;

// La anotación @Service indica a Spring que esta clase contiene la lógica de negocio.
// Funciona como un intermediario entre el Controlador (que recibe las peticiones) 
// y el Repositorio (que maneja los datos en la base de datos).
@Service

// La anotación @Transactional asegura que las operaciones de base de datos se ejecuten 
// de forma segura. Si ocurre un error a mitad de un proceso, deshace los cambios 
// para que la base de datos no quede inconsistente.
@Transactional
public class ModeracionService {

    // @Autowired inyecta el repositorio automáticamente. 
    // Evita que usted tenga que crear la instancia manualmente (ej. usando "new").
    @Autowired
    private ModeracionRepository moderacionRepository;

    // Recupera una lista con todos los registros de moderación guardados en la tabla.
    public List<Moderacion> findAll() {
        return moderacionRepository.findAll();
    }

    // Busca un registro de moderación específico utilizando su ID.
    // El método .get() se utiliza para extraer el objeto de la envoltura "Optional" 
    // que devuelve la base de datos por defecto.
    public Moderacion findById(Long id) {
        return moderacionRepository.findById(id).get();
    }

    // Recibe un objeto Moderacion y lo guarda en la base de datos. 
    // Si el objeto ya tiene un ID existente, en lugar de crearlo de nuevo, lo actualiza.
    public Moderacion save(Moderacion moderacion) {
        return moderacionRepository.save(moderacion);
    }

    // Busca un registro por su ID y lo elimina permanentemente de la base de datos.
    public void deleteById(Long id) {
        moderacionRepository.deleteById(id);
    }
}

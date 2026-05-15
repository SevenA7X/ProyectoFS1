package com.example.gestion.gestion_usuarios.service;

import com.example.gestion.gestion_usuarios.model.Usuario;
import com.example.gestion.gestion_usuarios.dto.UsuarioDTO;
import com.example.gestion.gestion_usuarios.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    // 1. Método para obtener todos (LISTAR)
    public List<UsuarioDTO> obtenerTodos() {
        log.info("Obteniendo lista de todos los usuarios");
        return repository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // 2. Método para obtener uno por ID
    public UsuarioDTO obtenerPorId(Long id) {
    log.info("Buscando usuario con ID: {}", id);
    Usuario usuario = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
    return convertirADTO(usuario);
    }

    // 3. Método para CREAR (IE 2.2.1)
    public UsuarioDTO crear(UsuarioDTO dto) {
        log.info("Creando nuevo usuario: {}", dto.getEmail());
        Usuario usuario = new Usuario(null, dto.getNombreUsuario(), dto.getEmail(), dto.getPassword(), dto.getRol());
        usuario = repository.save(usuario);
        dto.setId(usuario.getId());
        return dto;
    }

    // 4. Método para ACTUALIZAR
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        log.info("Actualizando usuario ID: {}", id);
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede actualizar: Usuario no existe"));
        
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        
        repository.save(usuario);
        return convertirADTO(usuario);
    }

    // 5. Método para ELIMINAR
    public void eliminar(Long id) {
        log.info("Eliminando usuario ID: {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: Usuario no encontrado");
        }
        repository.deleteById(id);
    }

    // Método privado para convertir Entidad a DTO (Ejemplo 3: Desacoplamiento)
    private UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        return dto;
    }
}

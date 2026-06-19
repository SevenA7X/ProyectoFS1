package com.example.gestion.gestion_usuarios.service;

import com.example.gestion.gestion_usuarios.model.Usuario;
import com.example.gestion.gestion_usuarios.dto.UsuarioDTO;
import com.example.gestion.gestion_usuarios.repository.UsuarioRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;



@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        // CORREGIDO: Constructor limpio con comillas dobles y tipos correctos
        Usuario usuario = new Usuario(1L, "Juan Pérez", "juanperez@gmail.com", "123456", "CLIENTE");
        
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        // CORREGIDO: El servicio retorna una lista de UsuarioDTO
        List<UsuarioDTO> usuarios = usuarioService.obtenerTodos();
        
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L; // CORREGIDO: De Integer a Long
        Usuario usuario = new Usuario(1L, "Juan Pérez", "juanperez@gmail.com", "123456", "CLIENTE");
        
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // CORREGIDO: Método correcto del servicio
        UsuarioDTO found = usuarioService.obtenerPorId(id);
        
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        // El método crear() del servicio espera y retorna un UsuarioDTO
        UsuarioDTO dtoInput = new UsuarioDTO();
        dtoInput.setNombreUsuario("Juan Pérez");
        dtoInput.setEmail("juanperez@gmail.com");
        dtoInput.setPassword("123456");
        dtoInput.setRol("CLIENTE");

        Usuario usuarioGuardado = new Usuario(1L, "Juan Pérez", "juanperez@gmail.com", "123456", "CLIENTE");
        
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        // CORREGIDO: Llamada al método correcto pasándole el DTO
        UsuarioDTO saved = usuarioService.crear(dtoInput);
        
        assertNotNull(saved);
        assertEquals("Juan Pérez", saved.getNombreUsuario());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L; // CORREGIDO: De Integer a Long
        
        doNothing().when(usuarioRepository).deleteById(id);

        // CORREGIDO: Método correcto del servicio
        usuarioService.eliminar(id);
        
        verify(usuarioRepository, times(1)).deleteById(id);
    }
}

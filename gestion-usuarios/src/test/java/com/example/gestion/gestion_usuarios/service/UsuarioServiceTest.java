package com.example.gestion.gestion_usuarios.service;

import com.example.gestion.gestion_usuarios.model.Usuario;
import com.example.gestion.gestion_usuarios.dto.UsuarioDTO;
import com.example.gestion.gestion_usuarios.repository.UsuarioRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Habilita el uso de Mockito puro sin levantar el contexto pesado de Spring Boot
@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    // 1. Aislamos la dependencia (La Base de Datos / Repositorio)
    @Mock
    private UsuarioRepository usuarioRepository;

    // 2. Inyectamos de forma automática los mocks en el servicio bajo prueba
    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void obtenerTodos_DeberiaRetornarListaVacia_CuandoNoHayUsuarios() {
        // --- GIVEN ---
        when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());

        // --- WHEN ---
        List<UsuarioDTO> resultado = usuarioService.obtenerTodos();

        // --- THEN ---
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void obtenerTodos_DeberiaRetornarListaDeUsuariosDTO_CuandoExistenUsuarios() {
        // --- GIVEN ---
        Usuario usuario = new Usuario(1L, "Juan Pérez", "juanperez@gmail.com", "123456", "CLIENTE");
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        // --- WHEN ---
        List<UsuarioDTO> resultado = usuarioService.obtenerTodos();

        // --- THEN ---
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombreUsuario());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_DeberiaRetornarUsuarioDTO_CuandoIdExiste() {
        // --- GIVEN ---
        Long idBuscado = 1L;
        Usuario usuarioSimulado = new Usuario(idBuscado, "Juan Pérez", "juanperez@gmail.com", "123456", "CLIENTE");
        when(usuarioRepository.findById(idBuscado)).thenReturn(Optional.of(usuarioSimulado));

        // --- WHEN ---
        UsuarioDTO resultado = usuarioService.obtenerPorId(idBuscado);

        // --- THEN ---
        assertNotNull(resultado);
        assertEquals(idBuscado, resultado.getId());
        assertEquals("Juan Pérez", resultado.getNombreUsuario()); 
        verify(usuarioRepository, times(1)).findById(idBuscado);
    }

    @Test
    void crear_DeberiaGuardarYRetornarUsuarioDTO_CuandoDtoEsValido() {
        // --- GIVEN ---
        UsuarioDTO dtoInput = new UsuarioDTO();
        dtoInput.setNombreUsuario("Juan Pérez");
        dtoInput.setEmail("juanperez@gmail.com");
        dtoInput.setPassword("123456");
        dtoInput.setRol("CLIENTE");

        Usuario usuarioGuardado = new Usuario(1L, "Juan Pérez", "juanperez@gmail.com", "123456", "CLIENTE");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        // --- WHEN ---
        UsuarioDTO resultado = usuarioService.crear(dtoInput);

        // --- THEN ---
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Juan Pérez", resultado.getNombreUsuario());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void eliminar_DeberiaInvocarDelete_CuandoIdExiste() {
        // --- GIVEN ---
        Long idEliminar = 1L;
        when(usuarioRepository.existsById(idEliminar)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(idEliminar);

        // --- WHEN ---
        usuarioService.eliminar(idEliminar);

        // --- THEN ---
        verify(usuarioRepository, times(1)).existsById(idEliminar);
        verify(usuarioRepository, times(1)).deleteById(idEliminar);
    }
}

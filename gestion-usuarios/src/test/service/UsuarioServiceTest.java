package com.example.gestion.gestion_usuarios.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import biblioteca.salas.duoc.biblioteca.salas.duoc.model.Sala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.model.TipoSala;
import biblioteca.salas.duoc.biblioteca.salas.duoc.repository.SalaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceTest {

@Autowired
    private UsuarioService usuarioService;

    @MockBean
    private SalaRepository usuarioRepository;

    @Test
    public void testFindAll() {
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario(1, "Juan Pérez", 'juanperez@gmail.com', 123456, 'CLIENTE' new TipoUsuario())));

        List<Usuario> usuarios = usuarioService.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Usuario usuario = new Usuario(id, "Sala A", 30, 1, new TipoSala());
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(1, "Sala A", 30, 1, new TipoUsuario());
        when(usuarioRepository.save(usuario)).thenReturn(sala);

        Usuario saved = usuarioService.save(usuario);
        assertNotNull(saved);
        assertEquals("Sala A", saved.getNombreUsuario());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.deleteById(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }
}

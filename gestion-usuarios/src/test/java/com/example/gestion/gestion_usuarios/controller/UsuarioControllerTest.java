package com.example.gestion.gestion_usuarios.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import com.example.gestion.gestion_usuarios.dto.UsuarioDTO;
import com.example.gestion.gestion_usuarios.service.UsuarioService;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioDTO usuarioDTO; //

    @BeforeEach
    void setUp() {
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L); // 
        usuarioDTO.setNombreUsuario("Juan Pérez");
        usuarioDTO.setEmail("juanperez@gmail.com"); // CORREGIDO: Agregadas las comillas dobles
        usuarioDTO.setPassword("123456"); // CORREGIDO: Agregadas las comillas y formato String
        usuarioDTO.setRol("CLIENTE");
    }

    @Test
    public void testGetAllUsuarios() throws Exception {
        // CORREGIDO: Nombre del método del servicio y retorno de DTO
        when(usuarioService.obtenerTodos()).thenReturn(List.of(usuarioDTO));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreUsuario").value("Juan Pérez"));
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        // CORREGIDO: Nombre del método del servicio
        when(usuarioService.obtenerPorId(1L)).thenReturn(usuarioDTO);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreUsuario").value("Juan Pérez"));
    }

    @Test
    public void testCreateUsuario() throws Exception {
        // CORREGIDO: Nombre del método crear()
        when(usuarioService.crear(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreUsuario").value("Juan Pérez"));
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        // CORREGIDO: Nombre del método actualizar()
        when(usuarioService.actualizar(eq(1L), any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(put("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioDTO))) // CORREGIDO: Cambiado 'sala' por 'usuarioDto'
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombreUsuario").value("Juan Pérez"));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        // CORREGIDO: Nombre del método eliminar() y tipo Long
        doNothing().when(usuarioService).eliminar(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).eliminar(1L);
    }
}

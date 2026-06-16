package com.example.gestion.gestion_usuarios.repository;

import com.example.gestion.gestion_usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
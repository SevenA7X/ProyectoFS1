package com.example.gestion.gestion_usuarios.repository;

import com.example.gestion.gestion_usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // JPQL Personalizado
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Usuario findByEmailPersonalizado(@Param("email") String email);
}

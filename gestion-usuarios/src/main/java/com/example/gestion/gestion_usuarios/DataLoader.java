package com.example.gestion.gestion_usuarios;

import com.example.gestion.gestion_usuarios.model.Usuario;
import com.example.gestion.gestion_usuarios.repository.UsuarioRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar usuarios
        for (int i = 0; i < 50; i++) {
            Usuario usuario = new Usuario();
            usuario.setId((long) (i + 1));
            usuario.setNombreUsuario(faker.name().fullName());
            usuario.setEmail(faker.internet().emailAddress());
            usuario.setPassword(String.valueOf(faker.number().numberBetween(100000000, 999999999)));
            usuario.setRol(faker.options().option("ADMIN", "CLIENTE", "MODERADOR"));
            usuarioRepository.save(usuario);
        }

    }
}
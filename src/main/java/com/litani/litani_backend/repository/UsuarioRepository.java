package com.litani.litani_backend.repository;

import com.litani.litani_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método mágico de Spring Data para buscar un usuario por su nombre de usuario
    Optional<Usuario> findByUsername(String username);
}
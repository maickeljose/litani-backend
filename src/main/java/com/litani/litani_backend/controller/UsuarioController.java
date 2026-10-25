package com.litani.litani_backend.controller;

import com.litani.litani_backend.model.Usuario;
import com.litani.litani_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Login: POST a /api/usuarios/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuarioLogin) {
        System.out.println("Intentando iniciar sesión con el usuario: " + usuarioLogin.getUsername());

        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByUsername(usuarioLogin.getUsername());

        if (usuarioEncontrado.isPresent()) {
            System.out.println("¡Usuario encontrado en la BD!");
            Usuario u = usuarioEncontrado.get();

            if (u.getPassword().equals(usuarioLogin.getPassword())) {
                System.out.println("Contraseña correcta. Login exitoso.");
                return ResponseEntity.ok(Map.of(
            "message", "Bienvenido, " + u.getNombreCompleto(),
            "idUsuario", u.getId(),
            "username", u.getUsername(),
            "nombreCompleto", u.getNombreCompleto(),
            "idRol", u.getIdRol()
));
            } else {
                System.out.println("Contraseña incorrecta para el usuario: " + usuarioLogin.getUsername());
            }
        } else {
            System.out.println("El usuario no existe en la base de datos.");
        }

        return ResponseEntity.status(401).body(Map.of("message", "Usuario o contraseña incorrectos"));
    }
}
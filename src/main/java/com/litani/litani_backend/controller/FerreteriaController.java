package com.litani.litani_backend.controller;

import com.litani.litani_backend.model.Ferreteria;
import com.litani.litani_backend.repository.FerreteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ferreterias")
@CrossOrigin(origins = "*")
public class FerreteriaController {

    @Autowired
    private FerreteriaRepository ferreteriaRepository;

    // GET /api/ferreterias?q=cand  -> hasta 20 coincidencias por nombre o RIF
    @GetMapping
    public List<Ferreteria> buscar(@RequestParam(defaultValue = "") String q) {
        String texto = q.trim();
        if (texto.length() < 2) {
            return List.of();
        }
        return ferreteriaRepository
                .findTop20ByRazonSocialContainingIgnoreCaseOrRifContainingIgnoreCase(texto, texto);
    }
}
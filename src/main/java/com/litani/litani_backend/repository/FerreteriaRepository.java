package com.litani.litani_backend.repository;

import com.litani.litani_backend.model.Ferreteria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FerreteriaRepository extends JpaRepository<Ferreteria, Long> {
    List<Ferreteria> findTop20ByRazonSocialContainingIgnoreCaseOrRifContainingIgnoreCase(String razonSocial, String rif);
}
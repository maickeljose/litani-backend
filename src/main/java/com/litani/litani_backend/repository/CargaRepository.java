package com.litani.litani_backend.repository;

import com.litani.litani_backend.model.Carga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CargaRepository extends JpaRepository<Carga, Long> {

    Optional<Carga> findFirstByEstado(Carga.Estado estado);

    List<Carga> findTop30ByOrderByFechaInicioDesc();
}
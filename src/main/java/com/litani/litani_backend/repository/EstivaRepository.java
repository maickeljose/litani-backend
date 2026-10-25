package com.litani.litani_backend.repository;

import com.litani.litani_backend.dto.EstivaDto;
import com.litani.litani_backend.model.Estiva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstivaRepository extends JpaRepository<Estiva, Long> {

    @Query("select new com.litani.litani_backend.dto.EstivaDto(e.id, f.zona, f.nombre, f.estadosReferencia, e.numeroEstiva, e.capacidadMaxima, e.bultosActuales) "
        + "from Estiva e join Fila f on f.id = e.idFila order by f.orden asc, e.numeroEstiva asc")
    List<EstivaDto> listarConFila();

    @Modifying
    @Query("update Estiva e set e.bultosActuales = e.bultosActuales + :bultos where e.id = :id")
    int sumarBultos(@Param("id") Long id, @Param("bultos") int bultos);
}
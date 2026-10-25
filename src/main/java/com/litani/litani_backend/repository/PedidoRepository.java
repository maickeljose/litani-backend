package com.litani.litani_backend.repository;

import com.litani.litani_backend.dto.PedidoPendienteDto;
import com.litani.litani_backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    boolean existsByCodigo(String codigo);

    // Búsqueda por código (pantalla Buscar pedido)
    List<Pedido> findTop20ByCodigoContainingIgnoreCaseOrderByFechaChequeoDesc(String codigo);

    // Dashboard
    List<Pedido> findByFechaChequeoBetween(LocalDateTime desde, LocalDateTime hasta);

    List<Pedido> findByIdCargaOrderByFechaCargadoAsc(Long idCarga);

    long countByIdCarga(Long idCarga);

    @Query("select new com.litani.litani_backend.dto.PedidoPendienteDto(p.id, p.codigo, p.tipo, p.bultos, p.fechaChequeo, f.razonSocial, f.estadoRegion) "
            + "from Pedido p left join Ferreteria f on f.id = p.idFerreteria "
            + "where p.situacion = :situacion order by p.fechaChequeo asc")
    List<PedidoPendienteDto> buscarPorSituacion(@Param("situacion") Pedido.Situacion situacion);
}
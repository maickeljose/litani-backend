package com.litani.litani_backend.controller;

import com.litani.litani_backend.dto.EstivaDto;
import com.litani.litani_backend.dto.PedidoPendienteDto;
import com.litani.litani_backend.model.Estiva;
import com.litani.litani_backend.model.Pedido;
import com.litani.litani_backend.repository.EstivaRepository;
import com.litani.litani_backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UbicacionController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EstivaRepository estivaRepository;

    // Pedidos chequeados que aún no tienen ubicación
    @GetMapping("/pedidos/pendientes")
    public List<PedidoPendienteDto> pedidosPendientes() {
        return pedidoRepository.buscarPorSituacion(Pedido.Situacion.CHEQUEADO);
    }

    // Todas las estivas con su fila y su ocupación
    @GetMapping("/estivas")
    public List<EstivaDto> estivas() {
        return estivaRepository.listarConFila();
    }

    // Asignar ubicación: avisa si se excede la capacidad, pero no bloquea
    @PostMapping("/pedidos/{id}/ubicar")
    @Transactional
    public ResponseEntity<?> ubicar(@PathVariable Long id, @RequestBody Map<String, Long> datos) {
        Long idEstiva = datos.get("idEstiva");
        Long idUbicador = datos.get("idUbicador");
        if (idEstiva == null || idUbicador == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Faltan la estiva o el encargado"));
        }

        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return ResponseEntity.status(404).body(Map.of("message", "El pedido no existe"));
        }
        if (pedido.getSituacion() != Pedido.Situacion.CHEQUEADO) {
            return ResponseEntity.status(409).body(Map.of("message", "El pedido " + pedido.getCodigo() + " ya fue ubicado"));
        }

        Estiva estiva = estivaRepository.findById(idEstiva).orElse(null);
        if (estiva == null) {
            return ResponseEntity.status(404).body(Map.of("message", "La estiva no existe"));
        }

        pedido.setIdEstiva(idEstiva);
        pedido.setIdUbicador(idUbicador);
        pedido.setFechaUbicado(LocalDateTime.now());
        pedido.setSituacion(Pedido.Situacion.UBICADO);
        pedidoRepository.save(pedido);

        estivaRepository.sumarBultos(idEstiva, pedido.getBultos());

        int total = estiva.getBultosActuales() + pedido.getBultos();
        return ResponseEntity.ok(Map.of(
            "message", "Pedido ubicado",
            "bultosEnEstiva", total,
            "capacidad", estiva.getCapacidadMaxima(),
            "excedeCapacidad", total > estiva.getCapacidadMaxima()
        ));
    }
}
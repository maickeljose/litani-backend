package com.litani.litani_backend.controller;

import com.litani.litani_backend.model.Pedido;
import com.litani.litani_backend.repository.FerreteriaRepository;
import com.litani.litani_backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private FerreteriaRepository ferreteriaRepository;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> registrarPedido(@RequestBody Pedido pedido) {
        if (pedido.getCodigo() == null || pedido.getCodigo().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("message", "El código del pedido es obligatorio"));
        }
        if (pedido.getBultos() == null || pedido.getBultos() < 1) {
            return ResponseEntity.badRequest().body(Map.of("message", "Los bultos deben ser al menos 1"));
        }
        if (pedido.getIdChequeador() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Falta el chequeador"));
        }
        if (pedido.getIdFerreteria() == null || !ferreteriaRepository.existsById(pedido.getIdFerreteria())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Selecciona una ferretería válida"));
        }
        if (pedido.getTipo() == null) {
        pedido.setTipo(Pedido.TipoPedido.OUT);
    }

        String codigo = pedido.getCodigo().trim();
        if (pedidoRepository.existsByCodigo(codigo)) {
            return ResponseEntity.status(409).body(Map.of("message", "El pedido " + codigo + " ya fue registrado"));
        }

        pedido.setId(null);
        pedido.setCodigo(codigo);
        pedido.setFechaChequeo(LocalDateTime.now());
        pedido.setSituacion(Pedido.Situacion.CHEQUEADO);
        pedido.setFechaUbicado(null);
        pedido.setIdUbicador(null);
        pedido.setIdEstiva(null);
        pedido.setFechaCargado(null);

        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }
}
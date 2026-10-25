package com.litani.litani_backend.controller;

import com.litani.litani_backend.dto.EstivaDto;
import com.litani.litani_backend.model.Carga;
import com.litani.litani_backend.model.Ferreteria;
import com.litani.litani_backend.model.Pedido;
import com.litani.litani_backend.repository.CargaRepository;
import com.litani.litani_backend.repository.EstivaRepository;
import com.litani.litani_backend.repository.FerreteriaRepository;
import com.litani.litani_backend.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
public class BusquedaController {

    private final PedidoRepository pedidoRepository;
    private final EstivaRepository estivaRepository;
    private final FerreteriaRepository ferreteriaRepository;
    private final CargaRepository cargaRepository;

    public BusquedaController(PedidoRepository pedidoRepository,
                            EstivaRepository estivaRepository,
                            FerreteriaRepository ferreteriaRepository,
                            CargaRepository cargaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.estivaRepository = estivaRepository;
        this.ferreteriaRepository = ferreteriaRepository;
        this.cargaRepository = cargaRepository;
    }

    @GetMapping("/pedidos/buscar")
    public List<Map<String, Object>> buscar(@RequestParam String q) {
        String texto = q == null ? "" : q.trim();
        if (texto.isEmpty()) { return List.of(); }

        Map<Long, EstivaDto> estivas = new HashMap<>();
        for (EstivaDto e : estivaRepository.listarConFila()) { estivas.put(e.id(), e); }

        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Pedido p : pedidoRepository.findTop20ByCodigoContainingIgnoreCaseOrderByFechaChequeoDesc(texto)) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("id", p.getId());
            m.put("codigo", p.getCodigo());
            m.put("tipo", p.getTipo() == null ? "OUT" : p.getTipo().name());
            m.put("bultos", p.getBultos());
            m.put("situacion", p.getSituacion() == null ? null : p.getSituacion().name());
            m.put("nota", p.getNota());
            m.put("fechaChequeo", p.getFechaChequeo());
            m.put("fechaUbicado", p.getFechaUbicado());
            m.put("fechaCargado", p.getFechaCargado());

            String ferreteria = null;
            if (p.getIdFerreteria() != null) {
                Optional<Ferreteria> f = ferreteriaRepository.findById(p.getIdFerreteria());
                if (f.isPresent()) { ferreteria = f.get().getRazonSocial(); }
            }
            m.put("ferreteria", ferreteria);

            EstivaDto e = p.getIdEstiva() == null ? null : estivas.get(p.getIdEstiva());
            m.put("zona", e == null ? null : e.zona());
            m.put("fila", e == null ? null : e.fila());
            m.put("estiva", e == null ? null : e.numero());
            resultado.add(m);
        }
        return resultado;
    }

    // Marcar como cargado: entra en la carga abierta y libera los bultos de la estiva
    @PostMapping("/pedidos/{id}/cargar")
    @Transactional
    public ResponseEntity<?> cargar(@PathVariable Long id, @RequestBody Map<String, Long> datos) {
        Long idCargador = datos.get("idCargador");
        if (idCargador == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Falta el usuario que carga"));
        }
        Carga carga = cargaRepository.findFirstByEstado(Carga.Estado.ABIERTA).orElse(null);
        if (carga == null) {
            return ResponseEntity.status(409).body(Map.of("message",
                "No hay una carga abierta. Crea una nueva carga primero."));
        }
        Pedido p = pedidoRepository.findById(id).orElse(null);
        if (p == null) {
            return ResponseEntity.status(404).body(Map.of("message", "El pedido no existe"));
        }
        if (p.getSituacion() != Pedido.Situacion.UBICADO) {
            return ResponseEntity.status(409).body(Map.of("message",
                "Solo se puede cargar un pedido ubicado (estado actual: " + p.getSituacion() + ")"));
        }
        if (p.getIdEstiva() != null) {
            estivaRepository.sumarBultos(p.getIdEstiva(), -p.getBultos());
        }
        p.setSituacion(Pedido.Situacion.CARGADO);
        p.setFechaCargado(LocalDateTime.now());
        p.setIdCarga(carga.getId());
        p.setIdCargador(idCargador);
        pedidoRepository.save(p);
        return ResponseEntity.ok(Map.of("message",
            "Pedido " + p.getCodigo() + " cargado en " + carga.getCamion()));
    }
}
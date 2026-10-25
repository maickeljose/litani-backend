package com.litani.litani_backend.controller;

import com.litani.litani_backend.dto.EstivaDto;
import com.litani.litani_backend.model.Carga;
import com.litani.litani_backend.model.Ferreteria;
import com.litani.litani_backend.model.Pedido;
import com.litani.litani_backend.model.Usuario;
import com.litani.litani_backend.repository.CargaRepository;
import com.litani.litani_backend.repository.EstivaRepository;
import com.litani.litani_backend.repository.FerreteriaRepository;
import com.litani.litani_backend.repository.PedidoRepository;
import com.litani.litani_backend.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/cargas")
public class CargaController {

    public record NuevaCarga(String camion, String chofer, Long idUsuario) {}

    private final CargaRepository cargaRepository;
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final FerreteriaRepository ferreteriaRepository;
    private final EstivaRepository estivaRepository;

    public CargaController(CargaRepository cargaRepository, PedidoRepository pedidoRepository,
                           UsuarioRepository usuarioRepository, FerreteriaRepository ferreteriaRepository,
                           EstivaRepository estivaRepository) {
        this.cargaRepository = cargaRepository;
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ferreteriaRepository = ferreteriaRepository;
        this.estivaRepository = estivaRepository;
    }

    // Carga con todos sus pedidos y totales (sirve para la pantalla y para el reporte)
    private Map<String, Object> detalle(Carga c) {
        Map<Long, String> nombres = new HashMap<>();
        for (Usuario u : usuarioRepository.findAll()) { nombres.put(u.getId(), u.getNombreCompleto()); }
        Map<Long, EstivaDto> estivas = new HashMap<>();
        for (EstivaDto e : estivaRepository.listarConFila()) { estivas.put(e.id(), e); }

        long pedOut = 0, bulOut = 0, pedPop = 0, bulPop = 0;
        List<Map<String, Object>> items = new ArrayList<>();
        for (Pedido p : pedidoRepository.findByIdCargaOrderByFechaCargadoAsc(c.getId())) {
            int b = p.getBultos() == null ? 0 : p.getBultos();
            boolean pop = p.getTipo() == Pedido.TipoPedido.POP;
            if (pop) { pedPop++; bulPop += b; } else { pedOut++; bulOut += b; }

            String ferreteria = null;
            if (p.getIdFerreteria() != null) {
                Optional<Ferreteria> f = ferreteriaRepository.findById(p.getIdFerreteria());
                if (f.isPresent()) { ferreteria = f.get().getRazonSocial(); }
            }
            EstivaDto e = p.getIdEstiva() == null ? null : estivas.get(p.getIdEstiva());

            Map<String, Object> m = new LinkedHashMap<>();
            m.put("id", p.getId());
            m.put("codigo", p.getCodigo());
            m.put("tipo", pop ? "POP" : "OUT");
            m.put("bultos", b);
            m.put("ferreteria", ferreteria);
            m.put("ubicacion", e == null ? null : e.fila() + " · Estiva " + e.numero());
            m.put("fechaCargado", p.getFechaCargado());
            m.put("cargadoPor", nombres.get(p.getIdCargador()));
            items.add(m);
        }

        Map<String, Object> carga = new LinkedHashMap<>();
        carga.put("id", c.getId());
        carga.put("camion", c.getCamion());
        carga.put("chofer", c.getChofer());
        carga.put("estado", c.getEstado().name());
        carga.put("fechaInicio", c.getFechaInicio());
        carga.put("fechaCierre", c.getFechaCierre());
        carga.put("creadoPor", nombres.get(c.getIdCreador()));
        carga.put("cerradoPor", nombres.get(c.getIdCierre()));

        Map<String, Object> totales = new LinkedHashMap<>();
        totales.put("pedidos", pedOut + pedPop);
        totales.put("bultos", bulOut + bulPop);
        totales.put("pedidosOut", pedOut);
        totales.put("bultosOut", bulOut);
        totales.put("pedidosPop", pedPop);
        totales.put("bultosPop", bulPop);

        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("carga", carga);
        resp.put("pedidos", items);
        resp.put("totales", totales);
        return resp;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> crear(@RequestBody NuevaCarga datos) {
        String camion = datos.camion() == null ? "" : datos.camion().trim();
        if (camion.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Escribe el camión (placa o nombre)"));
        }
        if (datos.idUsuario() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Falta el usuario"));
        }
        Optional<Carga> abierta = cargaRepository.findFirstByEstado(Carga.Estado.ABIERTA);
        if (abierta.isPresent()) {
            return ResponseEntity.status(409).body(Map.of("message",
                "Ya hay una carga abierta (" + abierta.get().getCamion() + "). Finalízala antes de crear otra."));
        }
        Carga c = new Carga();
        c.setCamion(camion);
        String chofer = datos.chofer() == null ? "" : datos.chofer().trim();
        c.setChofer(chofer.isEmpty() ? null : chofer);
        c.setEstado(Carga.Estado.ABIERTA);
        c.setFechaInicio(LocalDateTime.now());
        c.setIdCreador(datos.idUsuario());
        cargaRepository.save(c);
        return ResponseEntity.ok(Map.of("message", "Carga iniciada para " + camion, "id", c.getId()));
    }

    @GetMapping("/activa")
    public Map<String, Object> activa() {
        Optional<Carga> c = cargaRepository.findFirstByEstado(Carga.Estado.ABIERTA);
        if (c.isEmpty()) {
            Map<String, Object> vacio = new HashMap<>();
            vacio.put("carga", null);
            return vacio;
        }
        return detalle(c.get());
    }

    @GetMapping
    public List<Map<String, Object>> historial() {
        List<Map<String, Object>> lista = new ArrayList<>();
        for (Carga c : cargaRepository.findTop30ByOrderByFechaInicioDesc()) {
            long pedidos = 0, bultos = 0;
            for (Pedido p : pedidoRepository.findByIdCargaOrderByFechaCargadoAsc(c.getId())) {
                pedidos++;
                bultos += p.getBultos() == null ? 0 : p.getBultos();
            }
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("id", c.getId());
            m.put("camion", c.getCamion());
            m.put("chofer", c.getChofer());
            m.put("estado", c.getEstado().name());
            m.put("fechaInicio", c.getFechaInicio());
            m.put("fechaCierre", c.getFechaCierre());
            m.put("pedidos", pedidos);
            m.put("bultos", bultos);
            lista.add(m);
        }
        return lista;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        Carga c = cargaRepository.findById(id).orElse(null);
        if (c == null) {
            return ResponseEntity.status(404).body(Map.of("message", "La carga no existe"));
        }
        return ResponseEntity.ok(detalle(c));
    }

    @PostMapping("/{id}/cerrar")
    @Transactional
    public ResponseEntity<?> cerrar(@PathVariable Long id, @RequestBody Map<String, Long> datos) {
        Carga c = cargaRepository.findById(id).orElse(null);
        if (c == null) {
            return ResponseEntity.status(404).body(Map.of("message", "La carga no existe"));
        }
        if (c.getEstado() != Carga.Estado.ABIERTA) {
            return ResponseEntity.status(409).body(Map.of("message", "La carga ya estaba cerrada"));
        }
        if (pedidoRepository.countByIdCarga(id) == 0) {
            return ResponseEntity.status(409).body(Map.of("message",
                "La carga no tiene pedidos. Agrega pedidos o cancélala."));
        }
        c.setEstado(Carga.Estado.CERRADA);
        c.setFechaCierre(LocalDateTime.now());
        c.setIdCierre(datos.get("idUsuario"));
        cargaRepository.save(c);
        return ResponseEntity.ok(Map.of("message", "Carga finalizada", "id", c.getId()));
    }

    // Solo se puede cancelar una carga abierta que aún no tiene pedidos
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        Carga c = cargaRepository.findById(id).orElse(null);
        if (c == null) {
            return ResponseEntity.status(404).body(Map.of("message", "La carga no existe"));
        }
        if (c.getEstado() != Carga.Estado.ABIERTA || pedidoRepository.countByIdCarga(id) > 0) {
            return ResponseEntity.status(409).body(Map.of("message",
                "Solo se puede cancelar una carga abierta y vacía"));
        }
        cargaRepository.delete(c);
        return ResponseEntity.ok(Map.of("message", "Carga cancelada"));
    }
}
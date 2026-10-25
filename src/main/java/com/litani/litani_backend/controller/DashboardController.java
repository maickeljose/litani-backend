package com.litani.litani_backend.controller;

import com.litani.litani_backend.model.Pedido;
import com.litani.litani_backend.model.Usuario;
import com.litani.litani_backend.repository.PedidoRepository;
import com.litani.litani_backend.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public DashboardController(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Acumulador de OUT (productividad) y POP (aparte)
    private static class Acum {
        long pedidosOut, bultosOut, pedidosPop, bultosPop;

        void sumar(Pedido p) {
            int b = p.getBultos() == null ? 0 : p.getBultos();
            if (p.getTipo() == Pedido.TipoPedido.POP) { pedidosPop++; bultosPop += b; }
            else { pedidosOut++; bultosOut += b; }
        }

        Map<String, Object> aMapa() {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("pedidosOut", pedidosOut);
            m.put("bultosOut", bultosOut);
            m.put("pedidosPop", pedidosPop);
            m.put("bultosPop", bultosPop);
            return m;
        }
    }

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard(@RequestParam String desde,
                                        @RequestParam String hasta,
                                        @RequestParam(required = false) Long idUsuario) {
        LocalDateTime d = LocalDate.parse(desde).atStartOfDay();
        LocalDateTime h = LocalDate.parse(hasta).plusDays(1).atStartOfDay().minusNanos(1);

        Map<Long, String> nombres = new HashMap<>();
        for (Usuario u : usuarioRepository.findAll()) { nombres.put(u.getId(), u.getNombreCompleto()); }

        Acum total = new Acum();
        Map<Long, Acum> porTrabajador = new LinkedHashMap<>();
        Map<LocalDate, Acum> porDia = new TreeMap<>();
        Map<String, Long> porSituacion = new LinkedHashMap<>();
        porSituacion.put("CHEQUEADO", 0L);
        porSituacion.put("UBICADO", 0L);
        porSituacion.put("CARGADO", 0L);

        for (Pedido p : pedidoRepository.findByFechaChequeoBetween(d, h)) {
            if (idUsuario != null && !idUsuario.equals(p.getIdChequeador())) { continue; }
            total.sumar(p);
            porTrabajador.computeIfAbsent(p.getIdChequeador(), k -> new Acum()).sumar(p);
            porDia.computeIfAbsent(p.getFechaChequeo().toLocalDate(), k -> new Acum()).sumar(p);
            if (p.getSituacion() != null) { porSituacion.merge(p.getSituacion().name(), 1L, Long::sum); }
        }

        List<Map<String, Object>> trabajadores = new ArrayList<>();
        porTrabajador.forEach((id, a) -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("id", id);
            m.put("nombre", nombres.getOrDefault(id, "Usuario " + id));
            m.putAll(a.aMapa());
            trabajadores.add(m);
        });
        trabajadores.sort((x, y) -> Long.compare((long) y.get("bultosOut"), (long) x.get("bultosOut")));

        List<Map<String, Object>> dias = new ArrayList<>();
        porDia.forEach((fecha, a) -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("fecha", fecha.toString());
            m.putAll(a.aMapa());
            dias.add(m);
        });

        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("resumen", total.aMapa());
        resp.put("porSituacion", porSituacion);
        resp.put("trabajadores", trabajadores);
        resp.put("dias", dias);
        return resp;
    }
}
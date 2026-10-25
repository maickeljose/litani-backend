package com.litani.litani_backend.dto;

import com.litani.litani_backend.model.Pedido;
import java.time.LocalDateTime;

public record PedidoPendienteDto(Long id, String codigo, Pedido.TipoPedido tipo, Integer bultos,
                                LocalDateTime fechaChequeo, String ferreteria, String destino) {}
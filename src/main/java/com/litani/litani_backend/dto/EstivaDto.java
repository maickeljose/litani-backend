package com.litani.litani_backend.dto;

public record EstivaDto(Long id, String zona, String fila, String estadosReferencia,
                        Integer numero, Integer capacidad, Integer ocupados) {}
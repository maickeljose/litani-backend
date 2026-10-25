package com.litani.litani_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estivas")
public class Estiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estiva")
    private Long id;

    @Column(name = "numero_estiva")
    private Integer numeroEstiva;

    @Column(name = "id_fila")
    private Long idFila;

    @Column(name = "capacidad_maxima")
    private Integer capacidadMaxima;

    @Column(name = "bultos_actuales")
    private Integer bultosActuales;

    public Estiva() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNumeroEstiva() { return numeroEstiva; }
    public void setNumeroEstiva(Integer numeroEstiva) { this.numeroEstiva = numeroEstiva; }
    public Long getIdFila() { return idFila; }
    public void setIdFila(Long idFila) { this.idFila = idFila; }
    public Integer getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(Integer capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }
    public Integer getBultosActuales() { return bultosActuales; }
    public void setBultosActuales(Integer bultosActuales) { this.bultosActuales = bultosActuales; }
}
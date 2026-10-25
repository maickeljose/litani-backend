package com.litani.litani_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "filas")
public class Fila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fila")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "estados_referencia")
    private String estadosReferencia;

    @Column(name = "zona")
    private String zona;

    @Column(name = "orden")
    private Integer orden;

    public Fila() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEstadosReferencia() { return estadosReferencia; }
    public void setEstadosReferencia(String estadosReferencia) { this.estadosReferencia = estadosReferencia; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }
}
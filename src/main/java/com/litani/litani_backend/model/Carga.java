package com.litani.litani_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cargas")
public class Carga {

    public enum Estado { ABIERTA, CERRADA }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carga")
    private Long id;

    @Column(name = "camion", nullable = false, length = 60)
    private String camion;

    @Column(name = "chofer", length = 80)
    private String chofer;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 10)
    private Estado estado;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_cierre")
    private LocalDateTime fechaCierre;

    @Column(name = "id_creador", nullable = false)
    private Long idCreador;

    @Column(name = "id_cierre")
    private Long idCierre;

    public Carga() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCamion() { return camion; }
    public void setCamion(String camion) { this.camion = camion; }
    public String getChofer() { return chofer; }
    public void setChofer(String chofer) { this.chofer = chofer; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDateTime getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDateTime fechaCierre) { this.fechaCierre = fechaCierre; }
    public Long getIdCreador() { return idCreador; }
    public void setIdCreador(Long idCreador) { this.idCreador = idCreador; }
    public Long getIdCierre() { return idCierre; }
    public void setIdCierre(Long idCierre) { this.idCierre = idCierre; }
}
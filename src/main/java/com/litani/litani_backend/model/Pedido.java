package com.litani.litani_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {

    public enum Situacion { CHEQUEADO, UBICADO, CARGADO }

    public enum TipoPedido { OUT, POP }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true, length = 30)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 10)
    private TipoPedido tipo;

    @Column(name = "id_ferreteria")
    private Long idFerreteria;

    @Column(name = "bultos", nullable = false)
    private Integer bultos;

    @Column(name = "fecha_chequeo", nullable = false)
    private LocalDateTime fechaChequeo;

    @Column(name = "id_chequeador", nullable = false)
    private Long idChequeador;

    @Column(name = "nota")
    private String nota;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacion", nullable = false, length = 20)
    private Situacion situacion;

    @Column(name = "fecha_ubicado")
    private LocalDateTime fechaUbicado;

    @Column(name = "id_ubicador")
    private Long idUbicador;

    @Column(name = "id_carga")
    private Long idCarga;

    @Column(name = "id_cargador")
    private Long idCargador;

    @Column(name = "id_estiva")
    private Long idEstiva;

    @Column(name = "fecha_cargado")
    private LocalDateTime fechaCargado;

    public Pedido() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Long getIdCarga() { return idCarga; }
    public void setIdCarga(Long idCarga) { this.idCarga = idCarga; }

    public Long getIdCargador() { return idCargador; }
    public void setIdCargador(Long idCargador) { this.idCargador = idCargador; }

    public TipoPedido getTipo() { return tipo; }
    public void setTipo(TipoPedido tipo) { this.tipo = tipo; }

    public Long getIdFerreteria() { return idFerreteria; }
    public void setIdFerreteria(Long idFerreteria) { this.idFerreteria = idFerreteria; }

    public Integer getBultos() { return bultos; }
    public void setBultos(Integer bultos) { this.bultos = bultos; }

    public LocalDateTime getFechaChequeo() { return fechaChequeo; }
    public void setFechaChequeo(LocalDateTime fechaChequeo) { this.fechaChequeo = fechaChequeo; }

    public Long getIdChequeador() { return idChequeador; }
    public void setIdChequeador(Long idChequeador) { this.idChequeador = idChequeador; }

    public String getNota() { return nota; }
    public void setNota(String nota) { this.nota = nota; }

    public Situacion getSituacion() { return situacion; }
    public void setSituacion(Situacion situacion) { this.situacion = situacion; }

    public LocalDateTime getFechaUbicado() { return fechaUbicado; }
    public void setFechaUbicado(LocalDateTime fechaUbicado) { this.fechaUbicado = fechaUbicado; }

    public Long getIdUbicador() { return idUbicador; }
    public void setIdUbicador(Long idUbicador) { this.idUbicador = idUbicador; }

    public Long getIdEstiva() { return idEstiva; }
    public void setIdEstiva(Long idEstiva) { this.idEstiva = idEstiva; }

    public LocalDateTime getFechaCargado() { return fechaCargado; }
    public void setFechaCargado(LocalDateTime fechaCargado) { this.fechaCargado = fechaCargado; }
}
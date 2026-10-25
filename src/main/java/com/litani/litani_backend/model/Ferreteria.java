package com.litani.litani_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ferreterias")
public class Ferreteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ferreteria")
    private Long id;

    @Column(name = "rif")
    private String rif;

    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "estado_region")
    private String estadoRegion;

    public Ferreteria() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRif() { return rif; }
    public void setRif(String rif) { this.rif = rif; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getEstadoRegion() { return estadoRegion; }
    public void setEstadoRegion(String estadoRegion) { this.estadoRegion = estadoRegion; }
}
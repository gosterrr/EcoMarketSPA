package com.ecomarket.cl.ecomarket.model;

import jakarta.persistence.*;

@Entity
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;

    @OneToOne
    @JoinColumn(name = "rut_administrador", referencedColumnName = "rut")
    private AdministradorTienda administrador;

    public Tienda() {}

    public Tienda(String direccion, AdministradorTienda administrador) {
        this.direccion = direccion;
        this.administrador = administrador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public AdministradorTienda getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorTienda administrador) {
        this.administrador = administrador;
    }
}


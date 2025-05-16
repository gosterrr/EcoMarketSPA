package com.ecomarket.cl.ecomarket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class AdministradorTienda extends Usuario {

    private boolean accesoTotal;

    @OneToOne(mappedBy = "administrador", cascade = jakarta.persistence.CascadeType.ALL)
    @JsonBackReference
    private Tienda tienda;

    public AdministradorTienda() {}

    public AdministradorTienda(String rut, String nombre, String correo, String direccion, String telefono, boolean accesoTotal) {
        super(rut, nombre, correo, direccion, telefono);
        this.accesoTotal = accesoTotal;
    }

    public boolean isAccesoTotal() {
        return accesoTotal;
    }

    public void setAccesoTotal(boolean accesoTotal) {
        this.accesoTotal = accesoTotal;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
}


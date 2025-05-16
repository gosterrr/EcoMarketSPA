package com.ecomarket.cl.ecomarket.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clienteRut;

    private double total;

    private double descuentoAplicado;

    private boolean cuponAplicado;

    private LocalDateTime fecha;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "boleta_producto",
        joinColumns = @JoinColumn(name = "boleta_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )

    private List<Producto> productos;

    public Boleta() {}

    public Boleta(String clienteRut, List<Producto> productos, double total, boolean cuponAplicado, double descuentoAplicado) {
        this.clienteRut = clienteRut;
        this.productos = productos;
        this.total = total;
        this.cuponAplicado = cuponAplicado;
        this.descuentoAplicado = descuentoAplicado;
        this.fecha = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getClienteRut() {
        return clienteRut;
    }

    public void setClienteRut(String clienteRut) {
        this.clienteRut = clienteRut;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isCuponAplicado() {
        return cuponAplicado;
    }

    public void setCuponAplicado(boolean cuponAplicado) {
        this.cuponAplicado = cuponAplicado;
    }

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}

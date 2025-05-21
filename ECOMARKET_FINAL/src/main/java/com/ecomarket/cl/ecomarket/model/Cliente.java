package com.ecomarket.cl.ecomarket.model;

import jakarta.persistence.*;

@Entity
public class Cliente extends Usuario {

    private String direccionEnvio;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private CarritoCompra carrito;

    public Cliente() {
        super();
        this.carrito = new CarritoCompra(this);
    }

    public Cliente(String rut, String nombre, String correo, String direccion, String telefono, 
                   String direccionEnvio, boolean carritoActivo) {
        super(rut, nombre, correo, direccion, telefono);
        this.direccionEnvio = direccionEnvio;
        this.carrito = new CarritoCompra(this);
        
    }


    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public CarritoCompra getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoCompra carrito) {
        this.carrito = carrito;
    }

    public void realizarPedido() {
        System.out.println("Pedido realizado exitosamente.");
        
    }

}

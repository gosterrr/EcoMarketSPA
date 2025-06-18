package com.ecomarket.cl.ecomarket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    private String rut;

    private String nombre;
    private String correo;
    private String direccion;
    private String telefono;

    public Usuario() {}

    public Usuario(String rut, String nombre, String correo, String direccion, String telefono) {
        setRut(rut);
        this.nombre = nombre;
        setCorreo(correo);
        this.direccion = direccion;
        setTelefono(telefono);
    }

    public String getRut() { return rut; }
    public void setRut(String rut) throws Exception {
        if (rut != null && rut.length() >= 9 && rut.length() <= 12) {
            this.rut = rut;
        } else {
            throw new Exception("El RUT debe tener entre 9 y 12 caracteres.");
        }
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) throws Exception {
        if (correo != null && correo.contains("@") &&
            (correo.endsWith(".com") || correo.endsWith(".cl"))) {
            this.correo = correo;
        } else {
            throw new Exception("El correo debe contener '@' y terminar en '.com' o '.cl'.");
        }
    }


    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) throws Exception {
        if (telefono != null && telefono.matches("\\d{9}")) {
            this.telefono = telefono;
        } else {
            throw new Exception("El teléfono debe contener exactamente 9 dígitos numéricos.");
        }
    }
}


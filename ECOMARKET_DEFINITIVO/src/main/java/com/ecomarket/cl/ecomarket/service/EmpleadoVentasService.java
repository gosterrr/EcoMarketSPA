package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.CuponDescuento;
import com.ecomarket.cl.ecomarket.model.EmpleadoVentas;
import com.ecomarket.cl.ecomarket.model.Tienda;
import com.ecomarket.cl.ecomarket.repository.CuponDescuentoRepository;
import com.ecomarket.cl.ecomarket.repository.EmpleadoVentasRepository;
import com.ecomarket.cl.ecomarket.repository.TiendaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoVentasService {

    @Autowired
    private EmpleadoVentasRepository empleadoVentasRepository;

    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;
    

    @Autowired
    private TiendaRepository tiendaRepository;

    public List<EmpleadoVentas> obtenerTodos() {
        return empleadoVentasRepository.findAll();
    }

    public Optional<EmpleadoVentas> obtenerPorRut(String rut) {
        return empleadoVentasRepository.findById(rut);
    }

    public EmpleadoVentas guardar(EmpleadoVentas empleadoVentas) {
        return empleadoVentasRepository.save(empleadoVentas);
    }

    public void eliminar(String rut) {
        empleadoVentasRepository.deleteById(rut);
    }

     public CuponDescuento generarCupon(String codigo, double descuento) {
        CuponDescuento cupon = new CuponDescuento(codigo, descuento);
        return cuponDescuentoRepository.save(cupon);
    }

    public EmpleadoVentas asignarTienda(String rutEmpleado, Long idTienda) {
        Optional<EmpleadoVentas> empleadoOpt = empleadoVentasRepository.findById(rutEmpleado);
        Optional<Tienda> tiendaOpt = tiendaRepository.findById(idTienda);

        if (empleadoOpt.isPresent() && tiendaOpt.isPresent()) {
            EmpleadoVentas empleado = empleadoOpt.get();
            empleado.setTienda(tiendaOpt.get());
            return empleadoVentasRepository.save(empleado);
        } else {
            throw new RuntimeException("Empleado o Tienda no encontrados.");
        }
    }
}

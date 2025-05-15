package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.AdministradorTienda;
import com.ecomarket.cl.ecomarket.model.EmpleadoVentas;
import com.ecomarket.cl.ecomarket.model.Tienda;
import com.ecomarket.cl.ecomarket.repository.AdministradorTiendaRepository;
import com.ecomarket.cl.ecomarket.repository.EmpleadoVentasRepository;
import com.ecomarket.cl.ecomarket.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorTiendaService {

    @Autowired
    private AdministradorTiendaRepository administradorTiendaRepository;

    @Autowired
    private EmpleadoVentasRepository empleadoVentasRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    public List<AdministradorTienda> obtenerTodos() {
        return administradorTiendaRepository.findAll();
    }

    public Optional<AdministradorTienda> obtenerPorRut(String rut) {
        return administradorTiendaRepository.findById(rut);
    }

    public AdministradorTienda guardar(AdministradorTienda administradorTienda) {
        return administradorTiendaRepository.save(administradorTienda);
    }

    public void eliminar(String rut) {
        administradorTiendaRepository.deleteById(rut);
    }

    public EmpleadoVentas asignarTiendaAEmpleado(String rutEmpleado, Long idTienda) {
        Optional<EmpleadoVentas> empleadoOpt = empleadoVentasRepository.findById(rutEmpleado);
        Optional<Tienda> tiendaOpt = tiendaRepository.findById(idTienda);

        if (empleadoOpt.isPresent() && tiendaOpt.isPresent()) {
            EmpleadoVentas empleado = empleadoOpt.get();
            empleado.setTienda(tiendaOpt.get());
            return empleadoVentasRepository.save(empleado);
        } else {
            throw new RuntimeException("Empleado o Tienda no encontrados");
        }
    }
}


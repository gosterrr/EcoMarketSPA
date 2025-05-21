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

    public EmpleadoVentas asignarEmpleadoATienda(String rutAdministrador, String rutEmpleado, Long idTienda) {
    Optional<AdministradorTienda> adminOpt = administradorTiendaRepository.findById(rutAdministrador);
    Optional<EmpleadoVentas> empleadoOpt = empleadoVentasRepository.findById(rutEmpleado);
    Optional<Tienda> tiendaOpt = tiendaRepository.findById(idTienda);

    if (adminOpt.isEmpty()) {
        throw new RuntimeException("Administrador no encontrado");
    }
    if (empleadoOpt.isEmpty()) {
        throw new RuntimeException("Empleado no encontrado");
    }
    if (tiendaOpt.isEmpty()) {
        throw new RuntimeException("Tienda no encontrada");
    }

    AdministradorTienda admin = adminOpt.get();
    Tienda tienda = tiendaOpt.get();

    
    if (!tienda.getAdministrador().getRut().equals(admin.getRut())) {
        throw new RuntimeException("El administrador no está asignado a esta tienda");
    }

    EmpleadoVentas empleado = empleadoOpt.get();
    empleado.setTienda(tienda);
    return empleadoVentasRepository.save(empleado);
}

}


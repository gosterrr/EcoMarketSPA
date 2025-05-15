package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.Tienda;
import com.ecomarket.cl.ecomarket.service.TiendaService;
import com.ecomarket.cl.ecomarket.service.AdministradorTiendaService;
import com.ecomarket.cl.ecomarket.model.AdministradorTienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @Autowired
    private AdministradorTiendaService administradorTiendaService;

    @GetMapping
    public List<Tienda> listar() {
        return tiendaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Optional<Tienda> obtener(@PathVariable Long id) {
        return tiendaService.obtenerPorId(id);
    }

    @PostMapping
    public Tienda crear(@RequestBody Tienda tienda) {
        return tiendaService.guardar(tienda);
    }

    @PostMapping("/crear-con-admin/{rut}")
    public Tienda crearConAdmin(@RequestBody Tienda tienda, @PathVariable String rut) {
        Optional<AdministradorTienda> adminOpt = administradorTiendaService.obtenerPorRut(rut);
        if (adminOpt.isPresent()) {
            tienda.setAdministrador(adminOpt.get());
            return tiendaService.guardar(tienda);
        } else {
            throw new RuntimeException("Administrador no encontrado con RUT: " + rut);
        }
    }

    @PutMapping("/{id}")
    public Tienda actualizar(@PathVariable Long id, @RequestBody Tienda tienda) {
        tienda.setId(id);
        return tiendaService.guardar(tienda);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tiendaService.eliminar(id);
    }
}

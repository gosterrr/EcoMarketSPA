package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.Tienda;
import com.ecomarket.cl.ecomarket.service.TiendaService;
import com.ecomarket.cl.ecomarket.service.AdministradorTiendaService;
import com.ecomarket.cl.ecomarket.model.AdministradorTienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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
    public Tienda obtener(@PathVariable Long id) {
    return tiendaService.obtenerPorId(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tienda no encontrada con ID: " + id));
}


    @PostMapping
    public Tienda crear(@RequestBody Tienda tienda) {
        return tiendaService.guardar(tienda);
    }

    @PostMapping("/crear-con-admin/{rut}")
    public Tienda crearConAdmin(@RequestBody Tienda tienda, @PathVariable String rut) {
    AdministradorTienda admin = administradorTiendaService.obtenerPorRut(rut)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador no encontrado con RUT: " + rut));
    tienda.setAdministrador(admin);
    return tiendaService.guardar(tienda);
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

    @PutMapping("/{id}/asignar-administrador/{rut}")
    public Tienda asignarAdministrador(@PathVariable Long id, @PathVariable String rut) {
    return tiendaService.asignarAdministrador(id, rut);
}

}

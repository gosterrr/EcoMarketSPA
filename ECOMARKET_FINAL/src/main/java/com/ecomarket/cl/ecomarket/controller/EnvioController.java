package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.Envio;
import com.ecomarket.cl.ecomarket.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/envios")
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> listar() { return envioService.obtenerTodos(); }

    @GetMapping("/{id}")
    public Envio obtener(@PathVariable Long id) {
    return envioService.obtenerPorId(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Envío no encontrado con ID: " + id));
}


    @PostMapping
    public Envio crear(@RequestBody Envio envio) { return envioService.guardar(envio); }

    @PutMapping("/{id}")
    public Envio actualizar(@PathVariable Long id, @RequestBody Envio envio) {
        envio.setId(id);
        return envioService.guardar(envio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { envioService.eliminar(id); }
}

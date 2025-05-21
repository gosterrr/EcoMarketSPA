package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.Usuario;
import com.ecomarket.cl.ecomarket.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar() { return usuarioService.obtenerTodos(); }

    @GetMapping("/{rut}")
    public Usuario obtener(@PathVariable String rut) {
    return usuarioService.obtenerPorRut(rut)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado con RUT: " + rut));
    }


    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) { return usuarioService.guardar(usuario); }

    @PutMapping("/{rut}")
public Usuario actualizar(@PathVariable String rut, @RequestBody Usuario usuario) {
    usuario.setRut(rut); 
    return usuarioService.guardar(usuario);
}

    @DeleteMapping("/{rut}")
    public void eliminar(@PathVariable String rut) {
        usuarioService.eliminar(rut);
    }
}

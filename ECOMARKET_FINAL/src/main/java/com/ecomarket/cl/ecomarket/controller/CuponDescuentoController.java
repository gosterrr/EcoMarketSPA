package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.CuponDescuento;
import com.ecomarket.cl.ecomarket.service.CuponDescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("/api/cupones-descuento")
public class CuponDescuentoController {

    @Autowired
    private CuponDescuentoService cuponDescuentoService;

    
    @GetMapping("/{codigo}")
    public CuponDescuento obtener(@PathVariable String codigo) {
    return cuponDescuentoService.obtenerPorCodigo(codigo)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cupón no encontrado con código: " + codigo));
}


    
    @PostMapping
    public CuponDescuento crear(@RequestBody CuponDescuento cupon) {
        return cuponDescuentoService.guardar(cupon);
    }

   
    @DeleteMapping("/{codigo}")
    public void eliminar(@PathVariable String codigo) {
        cuponDescuentoService.eliminar(codigo);
    }
}

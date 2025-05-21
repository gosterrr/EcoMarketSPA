package com.ecomarket.cl.ecomarket.controller;

import com.ecomarket.cl.ecomarket.model.Boleta;
import com.ecomarket.cl.ecomarket.model.CarritoCompra;
import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.service.CarritoCompraService;
import com.ecomarket.cl.ecomarket.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/carritos")
public class CarritoCompraController {

    @Autowired
    private CarritoCompraService carritoCompraService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<CarritoCompra> listar() {
        return carritoCompraService.obtenerTodos();
    }

    
    @GetMapping("/{rut}")
    public CarritoCompra obtener(@PathVariable String rut) {
    return carritoCompraService.obtenerPorId(rut)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado"));
}

    @PutMapping("/{rut}")
    public CarritoCompra actualizar(@PathVariable String rut, @RequestBody CarritoCompra carrito) {
        
        return carritoCompraService.guardar(carrito);
    }

    @DeleteMapping("/{rut}")
    public void eliminar(@PathVariable String rut) {
        carritoCompraService.eliminar(rut);
    }

    
    @PostMapping("/{clienteRut}/productos/{productoId}")
    public CarritoCompra agregarProducto(@PathVariable String clienteRut, @PathVariable Long productoId) {
    Producto producto = productoService.obtenerPorId(productoId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

    return carritoCompraService.agregarProducto(clienteRut, producto.getId());
}


    @PostMapping("/{clienteRut}/aplicar-cupon")
    public CarritoCompra aplicarCupon(@PathVariable String clienteRut, @RequestParam String codigo) {
    try {
        return carritoCompraService.aplicarCuponAlCarrito(clienteRut, codigo);
    } catch (RuntimeException ex) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}

@PostMapping("/{clienteRut}/realizar-compra")
public Boleta realizarCompra(@PathVariable String clienteRut) {
    try {
        return carritoCompraService.realizarCompra(clienteRut);
    } catch (RuntimeException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}


}

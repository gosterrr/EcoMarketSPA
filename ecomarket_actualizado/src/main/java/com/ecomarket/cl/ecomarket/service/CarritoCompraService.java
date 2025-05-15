package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.CarritoCompra;
import com.ecomarket.cl.ecomarket.model.Cliente;
import com.ecomarket.cl.ecomarket.model.CuponDescuento;
import com.ecomarket.cl.ecomarket.model.Producto;  
import com.ecomarket.cl.ecomarket.repository.CarritoCompraRepository;
import com.ecomarket.cl.ecomarket.repository.ClienteRepository;
import com.ecomarket.cl.ecomarket.repository.CuponDescuentoRepository;
import com.ecomarket.cl.ecomarket.repository.ProductoRepository;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarritoCompraService {

    @Autowired
    private CarritoCompraRepository carritoCompraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;  

    @Autowired
    private CuponDescuentoRepository cuponDescuentoRepository;

    
    public List<CarritoCompra> obtenerTodos() {
        return carritoCompraRepository.findAll();
    }

    
    public Optional<CarritoCompra> obtenerPorId(String rut) {
        return carritoCompraRepository.findByClienteRut(rut);
    }

    
    public CarritoCompra guardar(CarritoCompra carrito) {
        return carritoCompraRepository.save(carrito);
    }

   
    public void eliminar(String rut) {
        carritoCompraRepository.deleteByClienteRut(rut);
    }

    
    public CarritoCompra agregarProducto(String clienteRut, Long productoId) {
        Optional<Cliente> clienteOpt = clienteRepository.findByRut(clienteRut);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            CarritoCompra carrito = cliente.getCarrito();

          
            if (carrito == null) {
                carrito = new CarritoCompra(cliente); 
                cliente.setCarrito(carrito); 
            }

            Optional<Producto> productoOpt = productoRepository.findById(productoId); 
            if (productoOpt.isPresent()) {
                Producto producto = productoOpt.get();

               
                if (producto.getStock() > 0) {
                    
                    producto.setStock(producto.getStock() - 1);

                    
                    List<Producto> productosActuales = carrito.getProductos();
                    productosActuales.add(producto);
                    carrito.setProductos(productosActuales);

                    
                    carritoCompraRepository.save(carrito);

                    
                    productoRepository.save(producto);

                    return carrito;
                } else {
                    System.out.println("No hay stock disponible para el producto: " + producto.getNombre());
                    return null;
                }
            } else {
                System.out.println("Producto no encontrado.");
                return null;
            }
        }
        return null;  
    }

    public CarritoCompra aplicarCuponAlCarrito(String clienteRut, String codigoCupon) {
    Optional<CarritoCompra> carritoOpt = carritoCompraRepository.findByClienteRut(clienteRut);
    if (carritoOpt.isEmpty()) {
        throw new RuntimeException("Carrito no encontrado para el cliente con RUT: " + clienteRut);
    }

    CarritoCompra carrito = carritoOpt.get();

    Optional<CuponDescuento> cuponOpt = cuponDescuentoRepository.findById(codigoCupon);
    if (cuponOpt.isEmpty()) {
        throw new RuntimeException("Cupón no encontrado con código: " + codigoCupon);
    }

    CuponDescuento cupon = cuponOpt.get();

    if (cupon.isUtilizado()) {
        throw new RuntimeException("El cupón ya fue utilizado.");
    }

   
    double totalOriginal = carrito.getProductos().stream()
        .mapToDouble(Producto::getPrecio)
        .sum();

    carrito.setTotalCarro(totalOriginal);
    carrito.aplicarDescuento(cupon.getDescuento());

    cupon.setUtilizado(true);
    cuponDescuentoRepository.save(cupon);
    return carritoCompraRepository.save(carrito);
}
}
package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.Producto;
import com.ecomarket.cl.ecomarket.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() { return productoRepository.findAll(); }
    public Optional<Producto> obtenerPorId(Long id) { return productoRepository.findById(id); }
    public Producto guardar(Producto producto) { return productoRepository.save(producto); }
    public void eliminar(Long id) { productoRepository.deleteById(id); }

}

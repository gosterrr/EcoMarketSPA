package com.ecomarket.cl.ecomarket.repository;

import com.ecomarket.cl.ecomarket.model.CarritoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarritoCompraRepository extends JpaRepository<CarritoCompra, Long> {

    Optional<CarritoCompra> findByCliente_Rut(String rut);  

    void deleteByCliente_Rut(String rut);  
}

package com.ecomarket.cl.ecomarket.service;

import com.ecomarket.cl.ecomarket.model.AdministradorTienda;
import com.ecomarket.cl.ecomarket.model.Tienda;
import com.ecomarket.cl.ecomarket.repository.AdministradorTiendaRepository;
import com.ecomarket.cl.ecomarket.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private AdministradorTiendaRepository administradorTiendaRepository;

    public List<Tienda> obtenerTodas() {
        return tiendaRepository.findAll();
    }

    public Optional<Tienda> obtenerPorId(Long id) {
        return tiendaRepository.findById(id);
    }

    public Tienda guardar(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    public void eliminar(Long id) {
        tiendaRepository.deleteById(id);
    }

    public Tienda asignarAdministrador(Long idTienda, String rutAdministrador) {
        Optional<Tienda> tiendaOpt = tiendaRepository.findById(idTienda);
        Optional<AdministradorTienda> adminOpt = administradorTiendaRepository.findById(rutAdministrador);

        if (tiendaOpt.isPresent() && adminOpt.isPresent()) {
            Tienda tienda = tiendaOpt.get();
            tienda.setAdministrador(adminOpt.get());
            return tiendaRepository.save(tienda);
        } else {
            throw new RuntimeException("Tienda o Administrador no encontrados.");
        }
    }
}

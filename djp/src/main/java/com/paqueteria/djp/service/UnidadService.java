package com.paqueteria.djp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paqueteria.djp.model.Unidad;
import com.paqueteria.djp.repository.UnidadRepository;

@Service
public class UnidadService {

    private final UnidadRepository unidadRepository;

    public UnidadService(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    public List<Unidad> listarTodas() {
        return unidadRepository.findAll();
    }

    public List<Unidad> listarDisponibles() {
        return unidadRepository.findByDisponibleTrue();
    }

    public Optional<Unidad> buscarPorId(Long id) {
        return unidadRepository.findById(id);
    }

    public Unidad guardar(Unidad unidad) {
        if (unidad.getDisponible() == null) {
            unidad.setDisponible(true);
        }
        return unidadRepository.save(unidad);
    }

    public void eliminar(Long id) {
        unidadRepository.deleteById(id);
    }
}

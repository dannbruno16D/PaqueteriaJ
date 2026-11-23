package com.paqueteria.djp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paqueteria.djp.model.EstadoPaquete;
import com.paqueteria.djp.model.Paquete;
import com.paqueteria.djp.model.Unidad;
import com.paqueteria.djp.model.Usuario;
import com.paqueteria.djp.repository.PaqueteRepository;

@Service
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;

    public PaqueteService(PaqueteRepository paqueteRepository) {
        this.paqueteRepository = paqueteRepository;
    }

    public List<Paquete> listarTodos() {
        return paqueteRepository.findAllByOrderByFechaRegistroDesc();
    }

    public List<Paquete> listarPorEstado(EstadoPaquete estado) {
        return paqueteRepository.findByEstado(estado);
    }

    public Optional<Paquete> buscarPorId(Long id) {
        return paqueteRepository.findById(id);
    }

    public Optional<Paquete> buscarPorCodigo(String codigoRastreo) {
        return paqueteRepository.findByCodigoRastreo(codigoRastreo);
    }

    @Transactional
    public Paquete registrar(Paquete paquete) {
        if (paquete.getCodigoRastreo() == null || paquete.getCodigoRastreo().isBlank()) {
            String sufijo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            paquete.setCodigoRastreo("DJP-" + sufijo);
        }
        if (paquete.getEstado() == null) {
            paquete.setEstado(EstadoPaquete.REGISTRADO);
        }
        return paqueteRepository.save(paquete);
    }

    @Transactional
    public Paquete actualizarEstado(Long id, EstadoPaquete nuevoEstado) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paquete no encontrado con ID: " + id));
        paquete.setEstado(nuevoEstado);
        return paqueteRepository.save(paquete);
    }

    @Transactional
    public Paquete asignarEnvio(Long id, Usuario transportista, Unidad unidad) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paquete no encontrado con ID: " + id));
        paquete.setTransportista(transportista);
        paquete.setUnidad(unidad);
        if (transportista != null || unidad != null) {
            paquete.setEstado(EstadoPaquete.EN_RUTA);
        }
        return paqueteRepository.save(paquete);
    }

    @Transactional
    public void eliminar(Long id) {
        paqueteRepository.deleteById(id);
    }
}

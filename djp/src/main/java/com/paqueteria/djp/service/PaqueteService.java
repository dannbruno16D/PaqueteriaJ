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

    public String mensajePorDefecto(EstadoPaquete estado) {
        if (estado == null) {
            return "Registrado en centro operativo DJP";
        }
        return switch (estado) {
            case REGISTRADO -> "Registrado en sucursal · En espera de ingreso";
            case EN_ALMACEN -> "En almacén central · Clasificación de carga";
            case EN_RUTA -> "En Toluca · Aduana 1";
            case ENTREGADO -> "Entregado satisfactoriamente en destino";
            case INCIDENCIA -> "Incidencia reportada · Revisión en centro operativo";
        };
    }

    @Transactional
    public Paquete registrar(Paquete paquete) {
        String sufijo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        paquete.setCodigoRastreo("DJP-" + sufijo);

        if (paquete.getEstado() == null) {
            paquete.setEstado(EstadoPaquete.REGISTRADO);
        }
        if (paquete.getDetalleUbicacion() == null || paquete.getDetalleUbicacion().isBlank()) {
            paquete.setDetalleUbicacion(mensajePorDefecto(paquete.getEstado()));
        } else {
            paquete.setDetalleUbicacion(paquete.getDetalleUbicacion().trim());
        }
        if (paquete.getDescripcionContenido() == null || paquete.getDescripcionContenido().isBlank()) {
            paquete.setDescripcionContenido("Paquete general");
        }
        if (paquete.getTelefonoContacto() == null || paquete.getTelefonoContacto().isBlank()) {
            paquete.setTelefonoContacto("No especificado");
        }
        if (paquete.getCodigoPostal() == null || paquete.getCodigoPostal().isBlank()) {
            paquete.setCodigoPostal("00000");
        }

        paquete.agregarEventoHistorial(paquete.getEstado(), paquete.getDetalleUbicacion());
        return paqueteRepository.save(paquete);
    }

    @Transactional
    public Paquete actualizarEstado(Long id, EstadoPaquete nuevoEstado, String detalleUbicacion) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paquete no encontrado con ID: " + id));

        String ubicacionFinal = (detalleUbicacion != null && !detalleUbicacion.isBlank())
                ? detalleUbicacion.trim()
                : mensajePorDefecto(nuevoEstado);

        paquete.setEstado(nuevoEstado);
        paquete.setDetalleUbicacion(ubicacionFinal);
        paquete.agregarEventoHistorial(nuevoEstado, ubicacionFinal);

        return paqueteRepository.save(paquete);
    }

    @Transactional
    public Paquete asignarEnvio(Long id, Usuario transportista, Unidad unidad) {
        Paquete paquete = paqueteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paquete no encontrado con ID: " + id));

        paquete.setTransportista(transportista);
        paquete.setUnidad(unidad);

        if (transportista != null || unidad != null) {
            String ubicacionRuta = (paquete.getDetalleUbicacion() != null && !paquete.getDetalleUbicacion().isBlank())
                    ? paquete.getDetalleUbicacion()
                    : "En Toluca · Aduana 1";
            paquete.setEstado(EstadoPaquete.EN_RUTA);
            paquete.setDetalleUbicacion(ubicacionRuta);
            paquete.agregarEventoHistorial(EstadoPaquete.EN_RUTA, ubicacionRuta);
        }

        return paqueteRepository.save(paquete);
    }

    @Transactional
    public void eliminar(Long id) {
        paqueteRepository.deleteById(id);
    }
}

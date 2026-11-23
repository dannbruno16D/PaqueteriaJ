package com.paqueteria.djp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paqueteria.djp.model.EstadoPaquete;
import com.paqueteria.djp.model.Paquete;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {

    Optional<Paquete> findByCodigoRastreo(String codigoRastreo);

    List<Paquete> findByEstado(EstadoPaquete estado);

    List<Paquete> findAllByOrderByFechaRegistroDesc();
}

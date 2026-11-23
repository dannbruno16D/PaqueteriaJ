package com.paqueteria.djp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paqueteria.djp.model.HistorialEstado;

public interface HistorialEstadoRepository extends JpaRepository<HistorialEstado, Long> {

    List<HistorialEstado> findByPaqueteIdOrderByFechaEventoDescIdDesc(Long paqueteId);
}
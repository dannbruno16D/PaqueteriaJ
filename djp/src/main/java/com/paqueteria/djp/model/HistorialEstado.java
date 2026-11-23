package com.paqueteria.djp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial_estados")
public class HistorialEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paquete_id", nullable = false)
    private Paquete paquete;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    private EstadoPaquete estado;

    @Column(name = "detalle_ubicacion", nullable = false, length = 180)
    private String detalleUbicacion;

    @Column(name = "fecha_evento", nullable = false, updatable = false)
    private LocalDateTime fechaEvento;

    public HistorialEstado() {
    }

    public HistorialEstado(Paquete paquete, EstadoPaquete estado, String detalleUbicacion) {
        this.paquete = paquete;
        this.estado = estado;
        this.detalleUbicacion = detalleUbicacion;
    }

    @PrePersist
    protected void onCreate() {
        if (this.fechaEvento == null) {
            this.fechaEvento = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public EstadoPaquete getEstado() {
        return estado;
    }

    public void setEstado(EstadoPaquete estado) {
        this.estado = estado;
    }

    public String getDetalleUbicacion() {
        return detalleUbicacion;
    }

    public void setDetalleUbicacion(String detalleUbicacion) {
        this.detalleUbicacion = detalleUbicacion;
    }

    public LocalDateTime getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDateTime fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
}

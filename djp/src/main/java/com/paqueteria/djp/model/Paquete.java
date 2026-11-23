package com.paqueteria.djp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "paquetes")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_rastreo", nullable = false, unique = true, length = 30)
    private String codigoRastreo;

    @Column(nullable = false, length = 120)
    private String remitente;

    @Column(nullable = false, length = 120)
    private String destinatario;

    @Column(name = "telefono_contacto", length = 25)
    private String telefonoContacto;

    @Column(name = "codigo_postal", length = 15)
    private String codigoPostal;

    @Column(name = "descripcion_contenido", length = 160)
    private String descripcionContenido;

    @Column(name = "direccion_destino", nullable = false, length = 220)
    private String direccionDestino;

    @Column(name = "peso_kg", nullable = false)
    private Double pesoKg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    private EstadoPaquete estado;

    @Column(name = "detalle_ubicacion", length = 180)
    private String detalleUbicacion;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transportista_id")
    private Usuario transportista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_id")
    private Unidad unidad;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("fechaEvento DESC, id DESC")
    private List<HistorialEstado> historial = new ArrayList<>();

    public Paquete() {
    }

    @PrePersist
    protected void onCreate() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
        if (this.estado == null) {
            this.estado = EstadoPaquete.REGISTRADO;
        }
    }

    public void agregarEventoHistorial(EstadoPaquete estadoEvento, String ubicacionEvento) {
        HistorialEstado evento = new HistorialEstado(this, estadoEvento, ubicacionEvento);
        this.historial.add(evento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoRastreo() {
        return codigoRastreo;
    }

    public void setCodigoRastreo(String codigoRastreo) {
        this.codigoRastreo = codigoRastreo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDescripcionContenido() {
        return descripcionContenido;
    }

    public void setDescripcionContenido(String descripcionContenido) {
        this.descripcionContenido = descripcionContenido;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
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

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getTransportista() {
        return transportista;
    }

    public void setTransportista(Usuario transportista) {
        this.transportista = transportista;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public List<HistorialEstado> getHistorial() {
        return historial;
    }

    public void setHistorial(List<HistorialEstado> historial) {
        this.historial = historial;
    }
}
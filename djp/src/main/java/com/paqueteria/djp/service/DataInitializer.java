package com.paqueteria.djp.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.paqueteria.djp.model.EstadoPaquete;
import com.paqueteria.djp.model.Paquete;
import com.paqueteria.djp.model.Rol;
import com.paqueteria.djp.model.Unidad;
import com.paqueteria.djp.model.Usuario;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioService usuarioService;
    private final UnidadService unidadService;
    private final PaqueteService paqueteService;

    public DataInitializer(UsuarioService usuarioService, UnidadService unidadService, PaqueteService paqueteService) {
        this.usuarioService = usuarioService;
        this.unidadService = unidadService;
        this.paqueteService = paqueteService;
    }

    @Override
    public void run(String... args) {
        Usuario admin = usuarioService.guardar(
                new Usuario("Admin DJP", "admin", "1234", Rol.ADMIN, true));
        Usuario transportista1 = usuarioService.guardar(
                new Usuario("Carlos Mendoza", "cmendoza", "1234", Rol.TRANSPORTISTA, true));
        Usuario transportista2 = usuarioService.guardar(
                new Usuario("Valeria Ruiz", "vruiz", "1234", Rol.TRANSPORTISTA, true));

        Unidad unidad1 = unidadService.guardar(
                new Unidad("DJP-01", "Camioneta de Reparto", 850.0, true));
        Unidad unidad2 = unidadService.guardar(
                new Unidad("DJP-02", "Furgoneta Carga Media", 1500.0, true));

        Paquete p1 = new Paquete();
        p1.setCodigoRastreo("DJP-100001");
        p1.setRemitente("Almacén Atlacomulco");
        p1.setDestinatario("Sofía Herrera");
        p1.setDireccionDestino("Av. Isidro Fabela 120, Toluca");
        p1.setPesoKg(4.5);
        p1.setEstado(EstadoPaquete.EN_RUTA);
        p1.setTransportista(transportista1);
        p1.setUnidad(unidad1);
        paqueteService.registrar(p1);

        Paquete p2 = new Paquete();
        p2.setCodigoRastreo("DJP-100002");
        p2.setRemitente("Tienda Centro");
        p2.setDestinatario("Mateo Ortiz");
        p2.setDireccionDestino("Calle Morelos 45, Atlacomulco");
        p2.setPesoKg(2.2);
        p2.setEstado(EstadoPaquete.EN_ALMACEN);
        p2.setTransportista(transportista2);
        p2.setUnidad(unidad2);
        paqueteService.registrar(p2);

        Paquete p3 = new Paquete();
        p3.setCodigoRastreo("DJP-100003");
        p3.setRemitente("Logística Norte");
        p3.setDestinatario("Camila Navarro");
        p3.setDireccionDestino("Av. Tecnológico 88, Metepec");
        p3.setPesoKg(7.8);
        p3.setEstado(EstadoPaquete.REGISTRADO);
        p3.setTransportista(admin);
        paqueteService.registrar(p3);
    }
}

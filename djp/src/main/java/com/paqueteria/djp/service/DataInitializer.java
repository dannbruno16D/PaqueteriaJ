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
        p1.setRemitente("Almacén Atlacomulco");
        p1.setDestinatario("Sofía Herrera");
        p1.setTelefonoContacto("712-100-4411");
        p1.setCodigoPostal("50000");
        p1.setDescripcionContenido("Lámpara de buró");
        p1.setDireccionDestino("Av. Isidro Fabela 120, Toluca");
        p1.setPesoKg(4.5);
        p1.setEstado(EstadoPaquete.EN_RUTA);
        p1.setDetalleUbicacion("En Toluca · Aduana 1");
        p1.setTransportista(transportista1);
        p1.setUnidad(unidad1);
        paqueteService.registrar(p1);

        Paquete p2 = new Paquete();
        p2.setRemitente("Tienda Centro");
        p2.setDestinatario("Mateo Ortiz");
        p2.setTelefonoContacto("712-209-8832");
        p2.setCodigoPostal("50450");
        p2.setDescripcionContenido("Teclado mecánico");
        p2.setDireccionDestino("Calle Morelos 45, Atlacomulco");
        p2.setPesoKg(2.2);
        p2.setEstado(EstadoPaquete.EN_ALMACEN);
        p2.setDetalleUbicacion("En almacén central · Clasificación de carga");
        p2.setTransportista(transportista2);
        p2.setUnidad(unidad2);
        paqueteService.registrar(p2);

        Paquete p3 = new Paquete();
        p3.setRemitente("Logística Norte");
        p3.setDestinatario("Camila Navarro");
        p3.setTelefonoContacto("722-345-9012");
        p3.setCodigoPostal("52140");
        p3.setDescripcionContenido("Cafetera eléctrica");
        p3.setDireccionDestino("Av. Tecnológico 88, Metepec");
        p3.setPesoKg(7.8);
        p3.setEstado(EstadoPaquete.REGISTRADO);
        p3.setDetalleUbicacion("Registrado en sucursal · En espera de ingreso");
        p3.setTransportista(admin);
        paqueteService.registrar(p3);
    }
}
package com.paqueteria.djp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paqueteria.djp.model.EstadoPaquete;
import com.paqueteria.djp.model.Paquete;
import com.paqueteria.djp.model.Unidad;
import com.paqueteria.djp.model.Usuario;
import com.paqueteria.djp.service.PaqueteService;
import com.paqueteria.djp.service.UnidadService;
import com.paqueteria.djp.service.UsuarioService;

@Controller
@RequestMapping("/paquetes")
public class PaqueteController {

    private final PaqueteService paqueteService;
    private final UnidadService unidadService;
    private final UsuarioService usuarioService;

    public PaqueteController(PaqueteService paqueteService, UnidadService unidadService, UsuarioService usuarioService) {
        this.paqueteService = paqueteService;
        this.unidadService = unidadService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Paquete paquete) {
        paqueteService.registrar(paquete);
        return "redirect:/";
    }

    @PostMapping("/{id}/estado")
    public String actualizarEstado(@PathVariable Long id, @RequestParam EstadoPaquete estado) {
        paqueteService.actualizarEstado(id, estado);
        return "redirect:/";
    }

    @PostMapping("/{id}/asignar")
    public String asignarEnvio(
            @PathVariable Long id,
            @RequestParam(required = false) Long transportistaId,
            @RequestParam(required = false) Long unidadId) {

        Usuario transportista = (transportistaId != null)
                ? usuarioService.buscarPorId(transportistaId).orElse(null)
                : null;

        Unidad unidad = (unidadId != null)
                ? unidadService.buscarPorId(unidadId).orElse(null)
                : null;

        paqueteService.asignarEnvio(id, transportista, unidad);
        return "redirect:/";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        paqueteService.eliminar(id);
        return "redirect:/";
    }
}

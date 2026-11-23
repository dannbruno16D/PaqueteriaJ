package com.paqueteria.djp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paqueteria.djp.service.PaqueteService;
import com.paqueteria.djp.service.UnidadService;
import com.paqueteria.djp.service.UsuarioService;

@Controller
public class HomeController {

    private final PaqueteService paqueteService;
    private final UnidadService unidadService;
    private final UsuarioService usuarioService;

    public HomeController(PaqueteService paqueteService, UnidadService unidadService, UsuarioService usuarioService) {
        this.paqueteService = paqueteService;
        this.unidadService = unidadService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String inicio(@RequestParam(required = false) String codigo, Model model) {
        model.addAttribute("activePage", "inicio");
        model.addAttribute("totalPaquetes", paqueteService.listarTodos().size());
        model.addAttribute("totalUnidades", unidadService.listarTodas().size());
        model.addAttribute("totalTransportistas", usuarioService.listarTransportistasActivos().size());

        if (codigo != null && !codigo.isBlank()) {
            model.addAttribute("resultadoRastreo", paqueteService.buscarPorCodigo(codigo.trim()).orElse(null));
            model.addAttribute("codigoBuscado", codigo.trim());
        }

        return "index";
    }
}
package com.paqueteria.djp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paqueteria.djp.model.Unidad;
import com.paqueteria.djp.service.UnidadService;

@Controller
@RequestMapping("/unidades")
public class UnidadController {

    private final UnidadService unidadService;

    public UnidadController(UnidadService unidadService) {
        this.unidadService = unidadService;
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Unidad unidad) {
        unidadService.guardar(unidad);
        return "redirect:/";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        unidadService.eliminar(id);
        return "redirect:/";
    }
}

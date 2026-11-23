package com.paqueteria.djp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paqueteria.djp.model.Rol;
import com.paqueteria.djp.model.Unidad;
import com.paqueteria.djp.model.Usuario;
import com.paqueteria.djp.service.UnidadService;
import com.paqueteria.djp.service.UsuarioService;

@Controller
@RequestMapping("/unidades")
public class UnidadController {

    private final UnidadService unidadService;
    private final UsuarioService usuarioService;

    public UnidadController(UnidadService unidadService, UsuarioService usuarioService) {
        this.unidadService = unidadService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String vistaUnidades(Model model) {
        model.addAttribute("activePage", "unidades");
        model.addAttribute("unidades", unidadService.listarTodas());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("roles", Rol.values());
        return "unidades";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Unidad unidad) {
        unidadService.guardar(unidad);
        return "redirect:/unidades";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        unidadService.eliminar(id);
        return "redirect:/unidades";
    }

    @PostMapping("/usuarios/registrar")
    public String registrarUsuario(
            @RequestParam String nombreCompleto,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Rol rol) {
        Usuario nuevo = new Usuario(nombreCompleto.trim(), username.trim(), password, rol, true);
        usuarioService.guardar(nuevo);
        return "redirect:/unidades";
    }
}

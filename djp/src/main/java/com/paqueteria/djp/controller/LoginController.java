package com.paqueteria.djp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(required = false) String requiereSesion,
                               Model model) {
        if ("true".equals(requiereSesion)) {
            model.addAttribute("error", "Debes iniciar sesión para acceder a esa sección.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String usuario,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        String usuarioLimpio = usuario == null ? "" : usuario.trim();

        boolean esAdmin = "admin".equalsIgnoreCase(usuarioLimpio) && "1234".equals(password);
        boolean esOperador = "operador".equalsIgnoreCase(usuarioLimpio) && "1234".equals(password);

        if (esAdmin || esOperador) {
            session.setAttribute("usuarioLogueado", usuarioLimpio);
            session.setAttribute("rolUsuario", esAdmin ? "ADMIN" : "OPERADOR");

            Object urlDestino = session.getAttribute("urlDestino");
            session.removeAttribute("urlDestino");

            if (urlDestino != null && !urlDestino.toString().isBlank()
                    && !urlDestino.toString().startsWith("/login")) {
                return "redirect:" + urlDestino.toString();
            }
            return "redirect:/";
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos.");
        return "login";
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

package com.paqueteria.djp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        Object usuario = (session != null) ? session.getAttribute("usuarioLogueado") : null;

        if (usuario != null) {
            return true;
        }

        HttpSession nuevaSesion = request.getSession(true);
        String uri = request.getRequestURI();
        String query = request.getQueryString();
        String destino = (query != null && !query.isBlank()) ? uri + "?" + query : uri;
        nuevaSesion.setAttribute("urlDestino", destino);

        response.sendRedirect(request.getContextPath() + "/login?requiereSesion=true");
        return false;
    }
}
package com.paqueteria.djp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paqueteria.djp.model.Rol;
import com.paqueteria.djp.model.Usuario;
import com.paqueteria.djp.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> listarTransportistasActivos() {
        return usuarioRepository.findByRolAndActivoTrue(Rol.TRANSPORTISTA);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorUsuario(String usuario) {
        return usuarioRepository.findByUsuario(usuario);
    }

    public Usuario guardar(Usuario usuario) {
        if (usuario.getActivo() == null) {
            usuario.setActivo(true);
        }
        return usuarioRepository.save(usuario);
    }
}
package com.paqueteria.djp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paqueteria.djp.model.Rol;
import com.paqueteria.djp.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsuario(String usuario);

    List<Usuario> findByRolAndActivoTrue(Rol rol);
}

package com.gestion.usuarios.repository;

import com.gestion.usuarios.model.Usuario;
import com.gestion.usuarios.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email); // para login
    List<Usuario> findByRol(Rol rol); // para filtrar por rol
}

package com.gestion.usuarios.service;

import com.gestion.usuarios.model.Usuario;
import com.gestion.usuarios.model.Rol;
import com.gestion.usuarios.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public List<Usuario> obtenerPorRol(Rol rol) {
        return repository.findByRol(rol);
    }

    public Optional<Usuario> obtenerPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Optional<Usuario> actualizar(Long id, Usuario nuevo) {
        return repository.findById(id).map(usuario -> {
            usuario.setNombre(nuevo.getNombre());
            usuario.setEmail(nuevo.getEmail());
            usuario.setContraseña(nuevo.getContraseña());
            usuario.setRol(nuevo.getRol());
            return repository.save(usuario);
        });
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

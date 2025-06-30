package com.gestion.usuarios.controller;

import com.gestion.usuarios.model.Usuario;
import com.gestion.usuarios.model.Rol;
import com.gestion.usuarios.service.UsuarioService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @GetMapping("/rol/{tipo}")
    public List<Usuario> obtenerPorRol(@PathVariable Rol tipo) {
        return service.obtenerPorRol(tipo);
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return service.guardar(usuario);
    }

    @PutMapping("/{id}")
    public Optional<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario nuevo) {
        return service.actualizar(id, nuevo);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        boolean eliminado = service.eliminar(id);
        return eliminado ? "Usuario eliminado con éxito" : "Usuario no encontrado";
    }
}

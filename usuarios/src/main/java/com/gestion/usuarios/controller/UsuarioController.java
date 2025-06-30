package com.gestion.usuarios.controller;

import com.gestion.usuarios.model.Usuario;
import com.gestion.usuarios.model.Rol;
import com.gestion.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuario Controller v1", description = "Operaciones CRUD básicas para usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }
    @Operation(summary = "Obtener todos los usuarios")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente")
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return service.obtenerTodos();
    }

    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public Optional<Usuario> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @Operation(summary = "Obtener usuarios por rol")
    @ApiResponse(responseCode = "200", description = "Usuarios filtrados por rol")
    @GetMapping("/rol/{tipo}")
    public List<Usuario> obtenerPorRol(@PathVariable Rol tipo) {
        return service.obtenerPorRol(tipo);
    }

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente")
    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return service.guardar(usuario);
    }

    @Operation(summary = "Actualizar un usuario existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public Optional<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario nuevo) {
        return service.actualizar(id, nuevo);
    }

    @Operation(summary = "Eliminar un usuario por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario eliminado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        boolean eliminado = service.eliminar(id);
        return eliminado ? "Usuario eliminado con éxito" : "Usuario no encontrado";
    }
}

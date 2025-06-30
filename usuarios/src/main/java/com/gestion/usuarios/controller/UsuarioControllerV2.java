package com.gestion.usuarios.controller;

import com.gestion.usuarios.model.Usuario;
import com.gestion.usuarios.model.Rol;
import com.gestion.usuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.gestion.usuarios.assembler.GestionUsuariosModelAssembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/usuarios")
@Tag(name = "Usuario Controller v2", description = "Operaciones CRUD para usuarios con HATEOAS")
public class UsuarioControllerV2 {

    private final UsuarioService service;
    private final GestionUsuariosModelAssembler assembler;

    public UsuarioControllerV2(UsuarioService service, GestionUsuariosModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Operation(summary = "Obtener todos los usuarios con enlaces HATEOAS")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida")
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> obtenerTodos() {
        List<EntityModel<Usuario>> usuarios = service.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV2.class).obtenerTodos()).withSelfRel());
    }

    @Operation(summary = "Obtener un usuario por ID con enlaces HATEOAS")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = service.obtenerPorId(id);
        return usuarioOpt
                .map(assembler::toModel)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Operation(summary = "Obtener usuarios por rol con enlaces HATEOAS")
    @ApiResponse(responseCode = "200", description = "Usuarios filtrados por rol")
    @GetMapping("/rol/{tipo}")
    public CollectionModel<EntityModel<Usuario>> obtenerPorRol(@PathVariable Rol tipo) {
        List<EntityModel<Usuario>> usuarios = service.obtenerPorRol(tipo).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV2.class).obtenerPorRol(tipo)).withSelfRel());
    }

    @Operation(summary = "Crear un nuevo usuario con respuesta HATEOAS")
    @ApiResponse(responseCode = "201", description = "Usuario creado")
    @PostMapping
    public EntityModel<Usuario> guardar(@RequestBody Usuario usuario) {
        Usuario guardado = service.guardar(usuario);
        return assembler.toModel(guardado);
    }

    @Operation(summary = "Actualizar un usuario existente con respuesta HATEOAS")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public EntityModel<Usuario> actualizar(@PathVariable Long id, @RequestBody Usuario nuevo) {
        Optional<Usuario> actualizado = service.actualizar(id, nuevo);
        return actualizado
                .map(assembler::toModel)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
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

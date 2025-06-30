package com.gestion.usuarios.assembler;

import com.gestion.usuarios.controller.UsuarioControllerV2;
import com.gestion.usuarios.model.Usuario;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class GestionUsuariosModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    @NonNull
    public EntityModel<Usuario> toModel(@NonNull Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).obtenerPorId(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).obtenerTodos()).withRel("usuarios"),
                linkTo(methodOn(UsuarioControllerV2.class).obtenerPorRol(usuario.getRol())).withRel("usuariosPorRol"));
    }
}

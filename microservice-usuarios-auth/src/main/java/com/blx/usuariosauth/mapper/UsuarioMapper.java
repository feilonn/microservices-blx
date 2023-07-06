package com.blx.usuariosauth.mapper;

import com.blx.usuariosauth.dtos.UsuarioRequest;
import com.blx.usuariosauth.dtos.UsuarioResponse;
import com.blx.usuariosauth.models.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private ModelMapper mapper;

    public Usuario toUsuario(UsuarioRequest usuarioRequest) {
        return mapper.map(usuarioRequest, Usuario.class);
    }

    public UsuarioResponse toUsuarioResponse(Usuario usuario) {
        return mapper.map(usuario, UsuarioResponse.class);
    }
}

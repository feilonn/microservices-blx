package com.blx.usuariosauth.dtos;

import com.blx.usuariosauth.enums.Roles;
import lombok.Data;

@Data
public class UsuarioResponse {

    private String email;
    private String nome;
    private String cpf;
    private Roles role;
}

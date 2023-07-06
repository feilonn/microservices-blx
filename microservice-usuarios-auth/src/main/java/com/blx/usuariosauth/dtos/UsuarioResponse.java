package com.blx.usuariosauth.dtos;

import com.blx.usuariosauth.enums.ERoles;
import lombok.Data;

@Data
public class UsuarioResponse {

    private String email;
    private String nome;
    private String cpf;
    private ERoles role;
}

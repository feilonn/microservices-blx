package com.blx.vendas.dtos.usuario;

import com.blx.vendas.enums.ERoles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {
    private String email;
    private String nome;
    private String cpf;
    private ERoles role;
}

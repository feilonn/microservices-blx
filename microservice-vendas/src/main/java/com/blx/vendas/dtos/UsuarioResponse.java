package com.blx.vendas.dtos;

import com.blx.vendas.enums.ERoles;
import lombok.Data;

@Data
public class UsuarioResponse {

    private String email;
    private String nome;
    private String cpf;
    private ERoles role;
}

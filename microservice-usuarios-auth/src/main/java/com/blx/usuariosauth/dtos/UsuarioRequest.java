package com.blx.usuariosauth.dtos;

import com.blx.usuariosauth.enums.Roles;
import com.blx.usuariosauth.models.Endereco;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Roles role;

    private Endereco endereco;
}

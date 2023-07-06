package com.blx.usuariosauth.models;

import com.blx.usuariosauth.enums.ERoles;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String email;

    private String password;

    private String nome;

    private String cpf;

    @Enumerated(EnumType.STRING)
    private ERoles role;

    @Embedded
    private Endereco endereco;
}

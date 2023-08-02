package com.blx.vendas.models;

import com.blx.vendas.enums.ERoles;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "usuarios")
public class Usuario {

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private String email;
    private String nome;
    @Enumerated(EnumType.STRING)
    private ERoles role;
}

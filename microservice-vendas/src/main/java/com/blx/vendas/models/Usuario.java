package com.blx.vendas.models;

import com.blx.vendas.enums.ERoles;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    private ERoles role;
}

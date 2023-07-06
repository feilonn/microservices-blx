package com.blx.vendas.models;

import com.blx.vendas.enums.ERoles;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    private Long id;

    private String email;

    private ERoles role;
}

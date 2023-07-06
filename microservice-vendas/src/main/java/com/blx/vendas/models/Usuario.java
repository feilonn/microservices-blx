package com.blx.vendas.models;

import com.blx.vendas.enums.ERoles;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    private ERoles role;
}

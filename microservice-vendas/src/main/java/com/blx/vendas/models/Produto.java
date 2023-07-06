package com.blx.vendas.models;

import com.blx.vendas.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String descricao;

    private String titulo;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;
}
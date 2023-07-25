package com.blx.vendas.dtos.relatorios;

import com.blx.vendas.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class RelatorioProdutoCollection {
    private String descricao;
    private String titulo;
    private BigDecimal valor;
    private String status;
    private String nomeUsuario;
}

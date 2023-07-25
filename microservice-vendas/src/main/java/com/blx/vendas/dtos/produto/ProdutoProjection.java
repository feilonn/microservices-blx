package com.blx.vendas.dtos.produto;

import com.blx.vendas.enums.EStatus;
import com.blx.vendas.models.Usuario;

import java.math.BigDecimal;

public interface ProdutoProjection {
    Long getId();
    String getDescricao();
    String getTitulo();
    BigDecimal getValor();
    EStatus getStatus();
    Usuario getUsuario();

}

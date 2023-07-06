package com.blx.vendas.dtos;

import com.blx.vendas.enums.EStatus;
import com.blx.vendas.models.Categoria;
import com.blx.vendas.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {

    private String descricao;
    private String titulo;
    private BigDecimal valor;
    private EStatus status;
    private Usuario usuario;
    private Categoria categoria;
}

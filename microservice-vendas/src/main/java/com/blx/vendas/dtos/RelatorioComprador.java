package com.blx.vendas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioComprador {

    private Long id;
    private String comprador;
    private BigDecimal total_compra;
    private Date dataVenda;
    private String produtos;
}

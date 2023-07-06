package com.blx.vendas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EStatus {

    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    @Getter
    private final String descricao;
}

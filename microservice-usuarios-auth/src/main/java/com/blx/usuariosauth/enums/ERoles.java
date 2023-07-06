package com.blx.usuariosauth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ERoles {
    ADMIN("ADMIN"),
    VENDEDOR("VENDEDOR"),
    CLIENTE("CLIENTE");

    @Getter
    private final String descricao;
}

package com.blx.vendas.exceptions;

import java.io.IOException;

public class ErroLeitura extends RuntimeException {

    public ErroLeitura(String message, Throwable cause) {
        super(message, cause);
    }
}

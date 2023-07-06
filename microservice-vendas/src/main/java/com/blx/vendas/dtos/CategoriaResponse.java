package com.blx.vendas.dtos;

import com.blx.vendas.enums.EStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoriaResponse {
    private String titulo;
    private EStatus Status;
    private LocalDateTime dataCriacao;
}

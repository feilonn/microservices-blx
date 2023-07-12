package com.blx.vendas.dtos;


import com.blx.vendas.models.Produto;
import com.blx.vendas.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendasRequest {

    private List<Produto> produtos;
    private Usuario usuario;
}

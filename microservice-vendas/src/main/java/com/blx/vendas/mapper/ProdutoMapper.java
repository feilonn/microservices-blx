package com.blx.vendas.mapper;

import com.blx.vendas.dtos.ProdutoRequest;
import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.models.Produto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoMapper {

    private final ModelMapper modelMapper;

    public ProdutoResponse toProdutoResponse(Produto produto) {
        return modelMapper.map(produto, ProdutoResponse.class);
    }

    public Produto toProduto(ProdutoRequest produtoRequest) {
        return modelMapper.map(produtoRequest, Produto.class);
    }
}

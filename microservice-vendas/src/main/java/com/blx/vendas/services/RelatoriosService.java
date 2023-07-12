package com.blx.vendas.services;

import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.mapper.ProdutoMapper;
import com.blx.vendas.models.Produto;
import com.blx.vendas.repositories.VendasRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatoriosService {

    private final VendasRespository respository;
    private final ProdutoMapper produtoMapper;

    public List<ProdutoResponse> buscarProdutosVendidosPorUsuario(Long usuarioId) {

        List<Produto> produtos = respository.buscarProdutosVendidosPorUsuario(usuarioId);

        return produtos.stream().map(produtoMapper::toProdutoResponse).collect(Collectors.toList());
    }
}

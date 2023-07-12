package com.blx.vendas.services;

import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.dtos.produto.ProdutoProjection;
import com.blx.vendas.mapper.ProdutoMapper;
import com.blx.vendas.repositories.VendasRespository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatoriosService {

    private final VendasRespository repository;
    private final ProdutoMapper produtoMapper;
    private final ModelMapper modelMapper;

    public List<ProdutoResponse> buscarProdutosVendidosPorUsuario(Long idUsuario) {
        List<ProdutoProjection> results = new ArrayList<>(repository.buscarProdutosVendidosPorUsuario(idUsuario));

        return results.stream()
                .map(produtoProjection -> modelMapper.map(produtoProjection, ProdutoResponse.class))
                .collect(Collectors.toList());
    }
}

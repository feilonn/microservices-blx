package com.blx.vendas.services;

import com.blx.vendas.dtos.VendasRequest;
import com.blx.vendas.dtos.VendasResponse;
import com.blx.vendas.mapper.VendasMapper;
import com.blx.vendas.models.Produto;
import com.blx.vendas.models.Vendas;
import com.blx.vendas.repositories.VendasRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendasService {

    private final VendasRespository repository;
    private final ProdutoService produtoService;

    private final VendasMapper vendasMapper;

    public Page<VendasResponse> listar(Pageable pageable) {
        List<VendasResponse> listaVendas= repository
                .findAll(pageable)
                .stream()
                .map(vendasMapper::toVendasResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(listaVendas);
    }

    public VendasResponse adicionar(VendasRequest vendasRequest) {
        Vendas vendasModel = vendasMapper.toVendas(vendasRequest);

        List<Long> idsProdutos = vendasRequest.getProdutos()
                .stream()
                .map(Produto::getId)
                .collect(Collectors.toList());

        List<Produto> produtos = produtosDaVendaById(idsProdutos);

        List<BigDecimal> valoresProdutos = produtos
                .stream()
                .map(Produto::getValor)
                .collect(Collectors.toList());


        vendasModel.setTotalCompra(calculaSomatorioTotalVendas(valoresProdutos));
        vendasModel.setDataVenda(LocalDateTime.now());

        vendasModel.setProdutos(produtos);

        Vendas vendasSalva = repository.save(vendasModel);

        return vendasMapper.toVendasResponse(vendasSalva);
    }

    public VendasResponse buscarVendaPorId(Long vendaId) {
        Vendas venda = buscarPorId(vendaId);

        return vendasMapper.toVendasResponse(venda);
    }

    public Vendas buscarPorId(Long vendaId) {
        return repository.findById(vendaId).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    private BigDecimal calculaSomatorioTotalVendas(List<BigDecimal> valoresProdutos) {

        return valoresProdutos.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private List<Produto> produtosDaVendaById(List<Long> produtosId) {
       return produtosId.stream()
                .map(produtoService::buscarPorId)
                .collect(Collectors.toList());
    }

}

package com.blx.vendas.services;

import com.blx.vendas.dtos.ProdutoRequest;
import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.mapper.ProdutoMapper;
import com.blx.vendas.models.Produto;
import com.blx.vendas.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    private final ProdutoMapper produtoMapper;


    public Page<ProdutoResponse> listar(Pageable pageable) {
        List<ProdutoResponse> listaUsuario = repository
                .findAll(pageable)
                .stream()
                .map(produtoMapper::toProdutoResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(listaUsuario);
    }

    public ProdutoResponse buscarProdutoPorId(Long produtoId) {
        Produto produto = buscarPorId(produtoId);

        return produtoMapper.toProdutoResponse(produto);
    }

    public ProdutoResponse adicionar(ProdutoRequest usuarioRequest) {
        Produto usuarioModel = produtoMapper.toProduto(usuarioRequest);
        Produto usuarioSalvo = repository.save(usuarioModel);

        return produtoMapper.toProdutoResponse(usuarioSalvo);
    }

    public ProdutoResponse alterar(Long id, ProdutoRequest usuarioRequest) {
        Produto usuario = buscarPorId(id);
        BeanUtils.copyProperties(usuarioRequest, usuario, "id");

        Produto usuarioBanco = repository.save(usuario);

        return produtoMapper.toProdutoResponse(usuarioBanco);
    }

    public void deletar(Long id) {
        Produto usuario = buscarPorId(id);

        repository.delete(usuario);
    }

    public Produto buscarPorId(Long usuarioId) {
        return repository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}
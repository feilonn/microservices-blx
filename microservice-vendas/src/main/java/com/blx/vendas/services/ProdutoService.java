package com.blx.vendas.services;

import com.blx.vendas.clients.UsuarioClient;
import com.blx.vendas.dtos.ProdutoRequest;
import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.dtos.produto.ProdutoProjection;
import com.blx.vendas.enums.EStatus;
import com.blx.vendas.exceptions.BadRequestException;
import com.blx.vendas.exceptions.RecursoNaoEncontradoException;
import com.blx.vendas.mapper.ProdutoMapper;
import com.blx.vendas.models.Produto;
import com.blx.vendas.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    private final ProdutoMapper produtoMapper;

    private final UsuarioClient usuarioClient;

    private final CategoriaService categoriaService;


    public Page<ProdutoResponse> listar(Pageable pageable) {
        buscarTodosProdutosByUsuario(3L);
        List<ProdutoResponse> listaUsuario = repository
                .findAll(pageable)
                .stream()
                .filter(produto -> produto.getStatus() == EStatus.ATIVO)
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

    public List<Produto> buscarProdutosById(List<Long> produtoIds) {
        return produtoIds
                .stream()
                .map(id -> repository.findById(id).orElse(null))
                .collect(Collectors.toList());

    }

    public List<ProdutoResponse> buscarTodosProdutosByUsuario(Long usuarioId) {
        Boolean existsUsuario = usuarioClient.existsUsuarioById(usuarioId);

        if (existsUsuario) {
            List<Produto> allProdutosByVendedorId = repository.findAllProdutosByVendedorId(usuarioId);
            return produtoMapper.toProdutoResponseList(allProdutosByVendedorId);
        }

        return Collections.emptyList();
    }

    public List<ProdutoResponse> buscarProdutoPorCategoria(Long idCategoria) {
        Boolean existsCategoriaById = categoriaService.existsCategoriaById(idCategoria);

        if (existsCategoriaById) {
            return repository.findProdutoByCategoriaId(idCategoria)
                    .stream()
                    .map(produtoMapper::toProdutoResponse)
                    .collect(Collectors.toList());
        }

        throw new RecursoNaoEncontradoException(String.format("A categoria com o id %d, não foi encontrada.", idCategoria));
    }

    public void alteraStatusProduto(Long id, EStatus status) {
        Produto produto = buscarPorId(id);

        if (produto.getStatus() == status) {
            throw new BadRequestException(String.format("O produto ja se encontra com o status %s", status.getDescricao()));
        }

        produto.setStatus(status);

        repository.save(produto);
    }

}
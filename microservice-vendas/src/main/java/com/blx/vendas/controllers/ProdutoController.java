package com.blx.vendas.controllers;

import com.blx.vendas.dtos.ProdutoRequest;
import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.enums.EStatus;
import com.blx.vendas.exceptions.BadRequestException;
import com.blx.vendas.models.Produto;
import com.blx.vendas.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("produtos")
@RestController
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping
    public Page<ProdutoResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("{usuarioId}")
    public ProdutoResponse buscarProdutoPorId(@PathVariable Long usuarioId) {
        return service.buscarProdutoPorId(usuarioId);
    }

    @PostMapping
    public ProdutoResponse adicionar(@RequestBody ProdutoRequest usuarioRequest) {
        return service.adicionar(usuarioRequest);
    }

    @PutMapping("{usuarioId}")
    public ProdutoResponse alterar(@PathVariable Long usuarioId, @RequestBody ProdutoRequest usuarioRequest) {
        return service.alterar(usuarioId, usuarioRequest);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    //TODO Corrigir status de resposta (404, no content) quando o usuário nao existir
    @GetMapping("/produtos-vendedor/{idVendedor}")
    public List<ProdutoResponse> buscarTodosProdutosByUsuario(@PathVariable Long idVendedor) {
        return service.buscarTodosProdutosByUsuario(idVendedor);
    }

    @GetMapping("/produtos-categoria/{idCategoria}")
    public List<ProdutoResponse> buscarProdutoPorCategoria(@PathVariable Long idCategoria) {
        return service.buscarProdutoPorCategoria(idCategoria);
    }

    @PatchMapping("altera-status-produto/{idProduto}")
    public void alteraStatusProduto(@PathVariable Long idProduto, @RequestParam EStatus status) {
        service.alteraStatusProduto(idProduto, status);
    }
}

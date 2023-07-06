package com.blx.vendas.controllers;

import com.blx.vendas.dtos.ProdutoRequest;
import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.services.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public ProdutoResponse buscarUsuarioPorId(@PathVariable Long usuarioId) {
        return service.buscarUsuarioPorId(usuarioId);
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
}

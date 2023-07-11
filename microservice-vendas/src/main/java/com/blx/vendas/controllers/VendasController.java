package com.blx.vendas.controllers;

import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.dtos.VendasRequest;
import com.blx.vendas.dtos.VendasResponse;
import com.blx.vendas.models.Produto;
import com.blx.vendas.services.VendasService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendasController {

    private final VendasService service;

    @GetMapping
    public Page<VendasResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendasResponse adicionar(@RequestBody @Valid VendasRequest vendas) {
        return service.adicionar(vendas);
    }

    @GetMapping("{vendaId}")
    public VendasResponse buscarVendaPorId(@PathVariable Long vendaId) {
        return service.buscarVendaPorId(vendaId);
    }

    @GetMapping("compras-by-usuario/{idComprador}")
    public List<ProdutoResponse> buscarProdutosByComprador(@PathVariable Long idComprador) {
        return service.buscarProdutosByComprador(idComprador);
    }
}

package com.blx.vendas.controllers;

import com.blx.vendas.dtos.VendasRequest;
import com.blx.vendas.dtos.VendasResponse;
import com.blx.vendas.services.VendasService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendasController {

    private final VendasService service;

    @GetMapping
    public Page<VendasResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendasResponse adicionar(@RequestBody @Valid VendasRequest vendas) {
        return service.adicionar(vendas);
    }

    @GetMapping("{vendaId}")
    public VendasResponse buscarVendaPorId(@PathVariable Long vendaId) {
        return service.buscarVendaPorId(vendaId);
    }
}

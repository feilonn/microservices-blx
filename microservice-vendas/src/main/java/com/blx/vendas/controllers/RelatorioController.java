package com.blx.vendas.controllers;

import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.services.RelatoriosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatoriosService service;

    @GetMapping("/produtos-vendidos/{idUsuario}")
    public List<ProdutoResponse> buscarProdutosVendidosPorUsuario(@PathVariable Long idUsuario) {
        return service.buscarProdutosVendidosPorUsuario(idUsuario);
    }

}

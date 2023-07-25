package com.blx.vendas.controllers;

import com.blx.vendas.dtos.ProdutoResponse;
import com.blx.vendas.services.RelatoriosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/produtos-vendidos/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProdutoResponse> buscarProdutosVendidosPorUsuario(@PathVariable Long idUsuario) {
        return service.buscarProdutosVendidosPorUsuario(idUsuario);
    }

    @GetMapping("/produtos-vendidos/{idUsuario}")
    public ResponseEntity<byte[]> buscarProdutosVendidosPorUsuarioPdf(@PathVariable Long idUsuario) {
        return null;
    }

}

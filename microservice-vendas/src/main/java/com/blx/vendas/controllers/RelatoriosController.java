package com.blx.vendas.controllers;

import com.blx.vendas.services.RelatoriosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("relatorios")
@RestController
@RequiredArgsConstructor
public class RelatoriosController {

    private final RelatoriosService service;

    @GetMapping(value = "comprador", produces = MediaType.APPLICATION_PDF_VALUE)
    public void gerarRelatorioComprasByUsuario(HttpServletResponse response) {
        service.gerarRelatorioComprasByUsuario(response);
    }
}

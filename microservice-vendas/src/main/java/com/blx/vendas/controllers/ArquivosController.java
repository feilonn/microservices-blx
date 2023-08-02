package com.blx.vendas.controllers;

import com.blx.vendas.services.ArquivosService;
import com.blx.vendas.services.RelatoriosService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/arquivos")
@RequiredArgsConstructor
public class ArquivosController {

    private final ArquivosService service;

    @GetMapping("/ler-diretorio")
    public String leitorArquivoTxt() {
        try {
            service.salvarDadosTxtNoBanco();
        }catch(Exception ex) {
            throw new RuntimeException("Erro ao ler arquivo .txt", ex);
        }

        return "Deu certo";
    }

}

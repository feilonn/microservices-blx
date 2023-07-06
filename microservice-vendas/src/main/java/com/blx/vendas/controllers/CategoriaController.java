package com.blx.vendas.controllers;

import com.blx.vendas.dtos.CategoriaRequest;
import com.blx.vendas.dtos.CategoriaResponse;
import com.blx.vendas.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;

    @GetMapping
    public Page<CategoriaResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long categoriaId) {
        CategoriaResponse categoria = service.buscarCategoriaPorId(categoriaId);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponse adicionar(@RequestBody @Valid CategoriaRequest categoria) {
        return service.adicionar(categoria);
    }

    @PutMapping("/{categoriaId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaResponse alterar(@PathVariable @Valid Long categoriaId, @RequestBody CategoriaRequest novoUsuario) {
        return service.alterar(categoriaId, novoUsuario);
    }

    @DeleteMapping("/{categoriaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long categoriaId) {
        try {
            service.deletar(categoriaId);
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível deletar o categoria.");
        }
    }

}

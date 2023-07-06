package com.blx.usuariosauth.controllers;

import com.blx.usuariosauth.dtos.UsuarioRequest;
import com.blx.usuariosauth.dtos.UsuarioResponse;
import com.blx.usuariosauth.exceptions.IdentidadeJaUtilizada;
import com.blx.usuariosauth.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public Page<UsuarioResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long usuarioId) {
        UsuarioResponse usuario = service.buscarUsuarioPorId(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse adicionar(@RequestBody @Valid UsuarioRequest usuario) {
        return service.adicionar(usuario);
    }

    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse alterar(@PathVariable @Valid Long usuarioId, @RequestBody UsuarioRequest novoUsuario) {
        return service.alterar(usuarioId, novoUsuario);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long usuarioId) {
        try {
            service.deletar(usuarioId);
        } catch (Exception ex) {
            throw new IdentidadeJaUtilizada("Não foi possível deletar o usuario.");
        }
    }

    @GetMapping("exists/{usuarioId}")
    public Boolean existsUsuarioById(@PathVariable Long usuarioId) {
        return service.existsUsuarioById(usuarioId);
    }
}

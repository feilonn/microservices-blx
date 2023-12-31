package com.blx.vendas.clients;

import com.blx.vendas.dtos.usuario.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(url = "${app.services.ms-usuarios}", name = "ms-usuarios", path = "/usuarios")
public interface UsuarioClient {

    @GetMapping("exists/{usuarioId}")
    Boolean existsUsuarioById(@PathVariable Long usuarioId);

    @GetMapping("/{usuarioId}")
    UsuarioResponse buscarPorId(@PathVariable Long usuarioId);
}

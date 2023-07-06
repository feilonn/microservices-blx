package com.blx.vendas.clients;


//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@Component
//@FeignClient(name = "usuario", path = "${app.services.ms-usuarios}")
public interface UsuarioClient {

//    static final String PATH = "/usuarios";
//
//    @GetMapping("/exists/{usuarioId}")
//    Boolean existsUsuarioById(@PathVariable Long usuarioId);
}

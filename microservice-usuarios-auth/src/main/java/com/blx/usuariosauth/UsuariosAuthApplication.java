package com.blx.usuariosauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UsuariosAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuariosAuthApplication.class, args);
    }

}

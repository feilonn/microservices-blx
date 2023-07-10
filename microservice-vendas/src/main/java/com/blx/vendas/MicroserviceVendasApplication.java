package com.blx.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceVendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceVendasApplication.class, args);
	}

}

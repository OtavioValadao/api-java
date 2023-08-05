package com.bmo.processbmo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Gerenciamento de produtos Api", version = "1.7.0", description = "API desenvolvida para facilitar a integração de vários clientes que usam nossos serviços de gerenciamento de produtos."))

public class ProcessApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessApiApplication.class, args);
	}

}

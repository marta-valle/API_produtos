package br.com.cotiinformatica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration // Anotação para sinalizar ao springboot que é de configuração.
public class SwaggerConfig {
	
	
		@Bean //Anotação que diz que a configuração está sendo feita para O SWAGGER.
		public OpenAPI customOpenApi() {
			return new OpenAPI().components(new Components()).info(new Info().title("API de produtos e fornecedores - COTI Informática")
					.description("Sistema SpringBoot com SpringData e acesso ao PostegreSQL").version("v1"));
		}
	}



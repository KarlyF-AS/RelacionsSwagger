package org.example.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Tutores y Alumnos")
                        .version("1.0")
                        .description("API para gestionar tutores y alumnos usando Hibernate"))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentación adicional")
                        .url("https://example.com"));
    }
}

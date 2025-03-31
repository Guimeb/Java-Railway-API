package me.dio.api.doc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Avanade Decola Tech - 2025")
                        .description("API for account management.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Guilherme Morais")
                                .email("guimeb.007@gmail.com")));
    }
}
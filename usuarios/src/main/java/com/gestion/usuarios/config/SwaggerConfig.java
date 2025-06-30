package com.gestion.usuarios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                    .info(new Info()
                        .title("API Gestion Usuarios") /* Titulo que aparecera en la interfaz de Swagger */
                        .version("1.0") /* Version de nuestra API */
                        .description("Documentacion de la API Gestion Usuarios")); /* Descripcion visible en la UI */
    }
}

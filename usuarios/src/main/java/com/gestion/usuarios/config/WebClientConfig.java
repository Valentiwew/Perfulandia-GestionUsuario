package com.gestion.usuarios.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration // Indica que esta clase es una clase de configuración de Spring (define beans).
public class WebClientConfig {

    @Value("${microservicio.producto_venta.url}") // Inyecta el valor de la propiedad definida en application.properties.
    private String productoVentaUrl;

    // Declara un bean de tipo WebClient para que esté disponible en el contexto de Spring.
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(productoVentaUrl) // Configura la URL base con la propiedad inyectada.
                .build(); // Construye la instancia de WebClient.
    }
}

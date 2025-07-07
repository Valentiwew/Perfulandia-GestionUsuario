package com.gestion.usuarios.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service // Marca esta clase como un componente de servicio en Spring para inyección de dependencias.
public class ProductoVentaClient {

    private final WebClient webClient; // Cliente HTTP reactivo para hacer llamadas a otros microservicios.

    // Constructor que inyecta el WebClient configurado con la URL base de ProductoVenta.
    public ProductoVentaClient(WebClient webClient) {
        this.webClient = webClient;
    }

    // Método para obtener un producto por su ID desde el microservicio ProductoVenta.
    public Mono<String> getProductoPorId(Integer id) {
        return webClient.get() // Realiza un GET HTTP
                .uri("/{id}", id) // A la ruta /{id}, reemplazando {id} con el valor recibido
                .retrieve() // Ejecuta la petición y espera la respuesta
                .bodyToMono(String.class); // Convierte la respuesta en un Mono<String> (programación reactiva)
    }

    // Método para obtener la lista completa de productos desde ProductoVenta.
    public Mono<String> getAllProductos() {
        return webClient.get() // Realiza un GET HTTP
                .retrieve() // Ejecuta la petición y espera la respuesta
                .bodyToMono(String.class); // Convierte la respuesta en un Mono<String>
    }
}

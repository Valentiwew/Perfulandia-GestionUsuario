package com.gestion.usuarios;
import com.gestion.usuarios.service.ProductoVentaClient;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;

public class ProductoVentaClientTest {

    private MockWebServer mockWebServer;
    private ProductoVentaClient productoVentaClient;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        productoVentaClient = new ProductoVentaClient(webClient);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testGetProductoPorId() {
        mockWebServer.enqueue(new MockResponse()
                .setBody("{\"id\":1,\"nombre\":\"Valentina\"}")
                .addHeader("Content-Type", "application/json"));

        StepVerifier.create(productoVentaClient.getProductoPorId(1))
                .expectNext("{\"id\":1,\"nombre\":\"Valentina\"}")
                .verifyComplete();
    }

    @Test
    void testGetAllProductos() {
        mockWebServer.enqueue(new MockResponse()
                .setBody("[{\"id\":1,\"nombre\":\"Valentina\"}]")
                .addHeader("Content-Type", "application/json"));

        StepVerifier.create(productoVentaClient.getAllProductos())
                .expectNext("[{\"id\":1,\"nombre\":\"Valentina\"}]")
                .verifyComplete();
    }
}

package com.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/clientes/**").uri("lb://msclientes"))
                .route(r -> r.path("/cartoes/**").uri("lb://mscartao"))
                .route(r -> r.path("/avaliacao-credito/**").uri("lb://msavaliadorcredito"))
                .build();
    }
}

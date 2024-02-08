package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
    }

    @Bean
    public RouteLocator myStreamingServiceLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("ram", route -> route.path("/ram/**").uri("http://localhost:8080/"))
                .route("bank", route -> route.path("/bank/**").uri("http://localhost:8081/"))
                .route("provider", route -> route.path("/provider/**").uri("http://localhost:8082/"))
                .build();
    }
}
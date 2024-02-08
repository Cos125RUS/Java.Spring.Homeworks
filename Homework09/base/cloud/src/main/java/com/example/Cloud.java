package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Cloud {
    public static void main(String[] args) {
        SpringApplication.run(Cloud.class, args);
    }

    @Bean
    public RouteLocator noteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("site", r -> r.path("/**").uri("http://localhost:8080/"))
                .route("notes", r -> r.path("/notes/**").uri("http://localhost:8081/"))
                .build();
    }
}
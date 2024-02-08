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
    public RouteLocator siteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("site1", r -> r.path("/site1/**").uri("http://localhost:8081/"))
                .route("site2", r -> r.path("/site2/**").uri("http://localhost:8082/"))
                .route("site3", r -> r.path("/site3/**").uri("http://localhost:8083/"))
                .build();
    }
}
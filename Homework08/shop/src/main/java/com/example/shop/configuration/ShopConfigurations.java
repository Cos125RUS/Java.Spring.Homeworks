package com.example.shop.configuration;

import com.example.shop.model.Product;
import com.example.shop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShopConfigurations {
    private ProductRepository productRepository;

    @Bean
    public void init() {
        productRepository.save(new Product("Кроссовки", "Asics", "m1", 10000, 5));
    }

}

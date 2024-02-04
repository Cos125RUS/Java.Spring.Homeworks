package com.example.shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer count;


    public Product(String type, String brand, String model, Integer price, Integer count) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.count = count;
    }
}

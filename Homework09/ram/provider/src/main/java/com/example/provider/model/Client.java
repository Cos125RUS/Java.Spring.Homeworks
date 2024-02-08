package com.example.provider.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

/**
 * Клиенты
 */
@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private URI uri;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, name = "downloads_limit")
    private int limit;

    public Client(URI uri, String name, int limit) {
        this.uri = uri;
        this.name = name;
        this.limit = limit;
    }
}

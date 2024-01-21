package com.example.demo.model;

import lombok.Data;

/**
 * Пользователь!
 */
@Data
public class User {

    /**
     * Пользователь и его id
     */
    private int id;

    /**
     * Кем кличут
     */
    private String firstName;

    /**
     * Кто ты по батюшке
     */
    private String lastName;
}

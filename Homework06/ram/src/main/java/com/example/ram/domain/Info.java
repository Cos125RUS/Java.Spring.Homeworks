package com.example.ram.domain;

import lombok.Data;

/**
 * информация о списке персонажей
 */
@Data
public class Info {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}

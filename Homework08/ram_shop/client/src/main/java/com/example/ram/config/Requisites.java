package com.example.ram.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * Реквизиты
 */
@ConfigurationProperties(prefix = "requisites")
@Data
@Validated
@AllArgsConstructor
public class Requisites {
    private final int bank;
    private final int provider;
    private final BigDecimal price;
}

package com.example.ram.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Служебные ссылки
 */
@ConfigurationProperties(prefix = "links")
@Data
@Validated
@AllArgsConstructor
public class Links {
    private final String characters;
    private final String path;
    private final String transfer;
    private final String provider;
}

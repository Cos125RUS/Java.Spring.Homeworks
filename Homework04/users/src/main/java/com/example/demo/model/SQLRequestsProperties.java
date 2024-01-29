package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * SQL запросы, хранимые в application.yaml
 */
@ConfigurationProperties(prefix = "repository.requests")
//@ConfigurationPropertiesScan
@Data
@Validated
@AllArgsConstructor
public class SQLRequestsProperties {
    /**
     * Список всех пользователей
     */
    private String selectAll;
    /**
     * Сохранение пользователя в базе
     */
    private String save;
    /**
     * Удаление по id
     */
    private String deleteById;
    /**
     * Ещё один ненужный комментарий
     */
    private String update;
}

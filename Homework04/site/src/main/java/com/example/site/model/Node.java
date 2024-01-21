package com.example.site.model;


import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Заметка
 */
@Data
public class Node {
    /**
     * Текст заметки
     */
    private String message;
    /**
     * Дата создания
     */
    private String creationTime;

    /**
     * Конструктор lego с одной деталькой
     * @param message массаж
     */
    public Node(String message) {
        this.message = message;
        creationTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));
    }

}

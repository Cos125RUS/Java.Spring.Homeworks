package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Заметка
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime creation;

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }
}

package com.example.domain;

/**
 * Фабрика заметок
 */
public class NoteFactory {
    /**
     * Создать новую заметку
     * @param title
     * @param body
     * @return
     */
    public static Note createNote(String title, String body) {
        return new Note(title, body);
    }
}

package com.example.service;

import com.example.domain.Note;

import java.util.List;

/**
 * Интерфейс запросов к notes-микросервису
 */
public interface NoteUsingService {
    List<Note> getNoteList();
    Note getNote(int id);
    Note save(String id, String title, String text);
}

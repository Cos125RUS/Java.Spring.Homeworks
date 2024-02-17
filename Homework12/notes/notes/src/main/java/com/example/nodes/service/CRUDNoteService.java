package com.example.nodes.service;

import com.example.nodes.domain.Note;

import java.util.List;

public interface CRUDNoteService {
    Note addNote(Note note);
    List<Note> findAll();
    Note findById(Long id);
    Note update(Note note);
    void deleteById(Long id);
}

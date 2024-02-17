package com.example.nodes.service;

import com.example.nodes.domain.Note;
import com.example.nodes.repository.NoteRepository;
import io.micrometer.core.instrument.Counter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервис обработки заметок
 */
@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final Counter counter;
    private final FileGateway fileGateway;

    /**
     * Добавление новой заметки
     * @param note
     * @return
     */
    public Note addNote(Note note) {
        note.setCreation(LocalDateTime.now());
        note = noteRepository.save(note);
        counter.increment();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 8 - note.getId().toString().length(); i++){
            id.append("0");
        }
        fileGateway.writeToFile("note_" + id.append(note.getId()) + ".txt", note);
        return note;
    }

    /**
     * Получить список всех заметок
     * @return
     */
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    /**
     * Найти заметку по id
     * @param id
     * @return
     */
    public Note findById(Long id) {
        return noteRepository.findById(id).orElse(null);
    }

    /**
     * Обновить заметку
     * @param note
     * @return
     */
    public Note update(Note note){
        return noteRepository.save(note);
    }

    /**
     * Удалить заметку по id
     * @param id
     */
    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}

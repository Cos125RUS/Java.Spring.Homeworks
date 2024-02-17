package com.example.nodes.controller;

import com.example.nodes.domain.Note;
import com.example.nodes.repository.NoteRepository;
import com.example.nodes.service.FileGateway;
import com.example.nodes.service.NoteService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Ну, это наш рест контролёр
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    /**
     * Добавить заметку
     * @param note заметка
     * @return заметка
     */
    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.addNote(note), HttpStatus.CREATED);
    }

    /**
     * Получить список всех заметок
     * @return список заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> findAll() {
        return new ResponseEntity<>(noteService.findAll(), HttpStatus.OK);
    }

    /**
     * Найти заметку по id
     * @param id идентификатор заметки
     * @return заметка
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Note note = noteService.findById(id);
        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Изменить заметку
     * @param id идентификатор заметки
     * @param note заметка
     * @return обновлённая заметка
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateById(@PathVariable Long id, @RequestBody Note note) {
        Note old = noteService.findById(id);
        if (old != null) {
            note.setCreation(old.getCreation());
            note.setId(old.getId());
            return new ResponseEntity<>(noteService.update(note), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удалить заметку по id
     * @param id идентификатор заметки
     * @return пустой ответ
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Note note = noteService.findById(id);
        if (note != null) {
            noteService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

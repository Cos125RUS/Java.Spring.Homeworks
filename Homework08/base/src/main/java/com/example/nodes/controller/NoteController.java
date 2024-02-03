package com.example.nodes.controller;

import com.example.nodes.domain.Note;
import com.example.nodes.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ну, это наш рест контролёр
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class NoteController {
    private final NodeService nodeService;

    /**
     * Добавить заметку
     * @param note заметка
     * @return заметка
     */
    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        return nodeService.addNote(note);
    }

    /**
     * Получить список всех заметок
     * @return список заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> findAll() {
        return nodeService.findAll();
    }

    /**
     * Найти заметку по id
     * @param id идентификатор заметки
     * @return заметка
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return nodeService.findById(id);
    }

    /**
     * Изменить заметку
     * @param id идентификатор заметки
     * @param note заметка
     * @return обновлённая заметка
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateById(@PathVariable Long id, @RequestBody Note note) {
        return nodeService.updateById(id, note);
    }

    /**
     * Удалить заметку по id
     * @param id идентификатор заметки
     * @return пустой ответ
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return deleteById(id);
    }
}

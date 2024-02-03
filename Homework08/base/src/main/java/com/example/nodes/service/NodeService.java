package com.example.nodes.service;

import com.example.nodes.aspect.TrackUserAction;
import com.example.nodes.domain.Note;
import com.example.nodes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NodeService {
    private final NoteRepository noteRepository;

    /**
     * Добавить заметку
     * @param note заметка
     * @return заметка
     */
    @TrackUserAction
    public ResponseEntity<Note> addNote(Note note) {
        note.setCreation(LocalDateTime.now());
        return new ResponseEntity<>(noteRepository.save(note), HttpStatus.CREATED);
    }

    /**
     * Получить список всех заметок
     * @return список заметок
     */
    @TrackUserAction
    public ResponseEntity<List<Note>> findAll() {
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Найти заметку по id
     * @param id идентификатор заметки
     * @return заметка
     */
    @TrackUserAction
    public ResponseEntity<?> findById(Long id) {
        Note note = noteRepository.findById(id).orElse(null);
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
    @TrackUserAction
    public ResponseEntity<Note> updateById(Long id, Note note) {
        Note old = noteRepository.findById(id).orElse(null);
        if (old != null) {
            note.setCreation(old.getCreation());
            note.setId(old.getId());
            return new ResponseEntity<>(noteRepository.save(note), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Удалить заметку по id
     * @param id идентификатор заметки
     * @return пустой ответ
     */
    @TrackUserAction
    public ResponseEntity<Void> deleteById(Long id) {
        Note old = noteRepository.findById(id).orElse(null);
        if (old != null) {
            noteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

package com.example.nodes.controller;

import com.example.nodes.domain.Note;
import com.example.nodes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class NoteController {
    private final NoteRepository noteRepository;

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        note.setCreation(LocalDateTime.now());
        return new ResponseEntity<>(noteRepository.save(note), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Note>> findAll() {
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note != null) {
            return new ResponseEntity<>(note, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateById(@PathVariable Long id, @RequestBody Note note) {
        Note old = noteRepository.findById(id).orElse(null);
        if (old != null) {
            note.setCreation(old.getCreation());
            note.setId(old.getId());
            return new ResponseEntity<>(noteRepository.save(note), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Note old = noteRepository.findById(id).orElse(null);
        if (old != null) {
            noteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

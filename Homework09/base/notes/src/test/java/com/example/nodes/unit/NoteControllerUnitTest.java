package com.example.nodes.unit;

import com.example.nodes.controller.NoteController;
import com.example.nodes.domain.Note;
import com.example.nodes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * Юнит-тесты рест-контроллера заметок
 */
@ExtendWith(MockitoExtension.class)
public class NoteControllerUnitTest {
    @Mock
    public NoteRepository noteRepository;

    @InjectMocks
    public NoteController noteController;

    public List<Note> noteList;
    public Note note1;
    public Note note2;


    /**
     * Инициализация параметров
     */
    @BeforeEach
    public void init() {
        noteList = new ArrayList<>();

        note1 = new Note();
        note1.setId(1L);
        note1.setTitle("Title1");
        note1.setBody("Content1");
        LocalDateTime creation1 = LocalDateTime.now();
        note1.setCreation(creation1);

        note2 = new Note();
        note2.setId(2L);
        note2.setTitle("Title2");
        note2.setBody("Content2");
        LocalDateTime creation2 = LocalDateTime.now();
        note2.setCreation(creation2);
    }

    /**
     * Запрос всех заметок из БД
     */
    @Test
    public void findAllTest() {
//        given
        noteList.add(note1);
        noteList.add(note2);
        given(noteRepository.findAll()).willReturn(noteList);
//        when
        ResponseEntity<List<Note>> all = noteController.findAll();
//        then
        assertEquals(noteList, all.getBody());
        assertEquals(all.getBody().size(), 2);
    }

    /**
     * Добавление новой заметки
     */
    @Test
    public void addNoteTest() {
//        given
        given(noteRepository.save(note1)).willReturn(note1);
//        when
        Note saveNote = noteController.addNote(note1).getBody();
//        then
        assertEquals(note1, saveNote);
    }

    /**
     * Поиск заметки по id
     */
    @Test
    public void findByIdTest() {
//        given
        noteList.add(note1);
        noteList.add(note2);
        given(noteRepository.findById(1L)).willReturn(Optional.ofNullable(noteList.get(0)));
//        when
        ResponseEntity<?> findNote = noteController.findById(1L);
//        then
        assertEquals(findNote.getBody(), note1);
    }

    /**
     * Обновление параметров заметки
     */
    @Test
    public void updateByIdTest() {
        //        given
        noteList.add(note1);
        given(noteRepository.findById(1L)).willReturn(Optional.ofNullable(noteList.get(0)));
        given(noteRepository.save(note2)).willReturn(note2);
//        when
        Note findNote = (Note) noteController.findById(1L).getBody();
        note2.setId(Objects.requireNonNull(findNote).getId());
        note2.setCreation(findNote.getCreation());
        Note save = noteRepository.save(note2);
//        then
        assertEquals(save, note2);
    }
}

package com.example.nodes.integration;

import com.example.nodes.controller.NoteController;
import com.example.nodes.domain.Note;
import com.example.nodes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class NoteControllerIntegrationTest {
    @MockBean
    public NoteRepository noteRepository;
    @Autowired
    public NoteController noteController;

    @Test
    public void noteControllerIntegrationTest() {
//        given
        Note note1 = new Note("Title", "Content");
        given(noteRepository.save(note1)).willReturn(note1);
//        when
        Note saveNote = noteController.addNote(note1).getBody();
//        then
        assertEquals(note1.getTitle(), Objects.requireNonNull(saveNote).getTitle());
        assertEquals(note1.getBody(), Objects.requireNonNull(saveNote).getBody());
    }
}

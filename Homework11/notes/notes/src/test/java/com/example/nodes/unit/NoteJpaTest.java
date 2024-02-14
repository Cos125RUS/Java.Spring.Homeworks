package com.example.nodes.unit;

import com.example.nodes.domain.Note;
import com.example.nodes.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест JPA
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteJpaTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private NoteRepository noteRepository;

    /**
     * Поиск по id
     */
    @Test
    public void findByIdTest() {
        // given
        Note note = new Note("Title", "Content");
        entityManager.persist(note);
        entityManager.flush();

        // when
        Note found = noteRepository.findById(note.getId()).get();

        // then
        assertEquals(found.getId(), note.getId());
        assertEquals(found.getTitle(), note.getTitle());
        assertEquals(found.getBody(), note.getBody());
        assertEquals(found.getCreation(), note.getCreation());
    }
}

package com.example.nodes.unit;

import com.example.nodes.controller.NoteController;
import com.example.nodes.domain.Note;
import com.example.nodes.repository.NoteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тесты MVC
 */
@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerRequestTest {
    @MockBean
    private NoteRepository noteRepository;
    @Autowired
    private MockMvc mvc;
    public List<Note> noteList;
    public Note note1;
    public Note note2;

    /**
     * Инициализация параметров
     */
    @Before
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
     * Проверка отправки запроса на предоставление всех заметок
     * @throws Exception
     */
    @Test
    public void requestToHomePageTest() throws Exception {
//        given
        noteList.add(note1);
        noteList.add(note2);
        given(noteRepository.findAll()).willReturn(noteList);
//        when

//        then
        mvc.perform(get("/notes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is(note1.getTitle())))
                .andExpect(jsonPath("$[0].body", is(note1.getBody())))
                .andExpect(jsonPath("$[1].title", is(note2.getTitle())))
                .andExpect(jsonPath("$[1].body", is(note2.getBody())));
    }
}

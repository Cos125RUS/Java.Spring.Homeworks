package com.example.unit;

import com.example.controller.SiteController;
import com.example.domain.Note;
import com.example.service.SiteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест MVC
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SiteController.class)
public class SiteControllerRequestTest {
    @MockBean
    private SiteService service;
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
     * Тест на запрос главной страницы
     * @throws Exception
     */
    @Test
    public void requestToHomePageTest() throws Exception {
        mvc.perform(get("/site")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}

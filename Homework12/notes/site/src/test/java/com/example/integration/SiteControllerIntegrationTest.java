package com.example.integration;

import com.example.controller.SiteController;
import com.example.domain.Note;
import com.example.service.SiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Интеграционные тесты контролёра сайта
 */
@SpringBootTest
public class SiteControllerIntegrationTest {
    @MockBean
    public SiteService siteService;
    @Mock
    public Model model;
    @Autowired
    public SiteController siteController;
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
     * Интеграционный тест запроса главной страницы сайта
     */
    @Test
    public void getHomePageTest() {
//        given
        noteList.add(note1);
        noteList.add(note2);
        given(siteService.getNoteList()).willReturn(noteList);
        given(model.addAttribute("notes", noteList)).willReturn(model);
//        when
        siteController.home(model);
//        then
        verify(siteService).getNoteList();
        verify(model).addAttribute("notes", noteList);
    }
}

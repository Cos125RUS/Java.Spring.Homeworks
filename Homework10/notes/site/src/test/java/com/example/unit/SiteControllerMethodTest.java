package com.example.unit;

import com.example.controller.SiteController;
import com.example.domain.Note;
import com.example.service.SiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.annotation.ModelFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Юнит-тесты контроллера сайта
 */
@ExtendWith(MockitoExtension.class)
public class SiteControllerTest {
    @Mock
    public SiteService siteService;
    @Mock
    public Model model;
    @InjectMocks
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
     * Запрос главной страницы
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

    /**
     * Запрос страницы с данными заметки
     */
    @Test
    public void getNotePageTest() {
//        given
        given(siteService.getNote(1)).willReturn(note1);
        given(model.addAttribute("id", note1.getId())).willReturn(model);
        given(model.addAttribute("text", note1.getBody())).willReturn(model);
        given(model.addAttribute("title", note1.getTitle())).willReturn(model);
//        when
        siteController.add(1, model);
//        then
        verify(siteService).getNote(1);
        verify(model).addAttribute("id", note1.getId());
        verify(model).addAttribute("text", note1.getBody());
        verify(model).addAttribute("title", note1.getTitle());
    }

    /**
     * Тест обновления параметров заметки
     * @param id
     * @param title
     * @param text
     */
    @ParameterizedTest
    @CsvSource(value = {"1, New Title, New Content"})
    public void changeNoteTest(String id, String title, String text) {
//        given
        given(siteService.save(id, title, text)).willReturn(note1);
//        when
        siteController.push(id, title, text);
//        then
        verify(siteService).save(id, title, text);
    }
}

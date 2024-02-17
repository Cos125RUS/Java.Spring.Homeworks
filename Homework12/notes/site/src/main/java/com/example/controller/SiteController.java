package com.example.controller;

import com.example.domain.Note;
import com.example.service.SiteService;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Контролёр запросов на сайт
 */
@Controller
@RequestMapping("/site")
@RequiredArgsConstructor
public class SiteController {
    public final SiteService siteService;
    private final Timer timer;

    /**
     * Главная страница
     *
     * @param model
     * @return
     */
    @GetMapping
    public String home(Model model) {
        List<Note> notes = siteService.getNoteList();
        model.addAttribute("notes", notes);
        return "index";
    }

    /**
     * Страница создание новой заметки
     *
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String add(Model model) {
        return "note";
    }

    /**
     * Редактирование заметки
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable int id, Model model) {
        AtomicReference<Note> note = new AtomicReference<>();
        timer.record(() -> note.set(siteService.getNote(id)));
        model.addAttribute("id", note.get().getId());
        model.addAttribute("text", note.get().getBody());
        model.addAttribute("title", note.get().getTitle());
        return "note";
    }

    /**
     * Изменение заметки
     *
     * @param id
     * @param title
     * @param text
     * @return
     */
    @PostMapping("/push")
    public String push(@RequestParam("note_id") String id,
                       @RequestParam("note_title") String title,
                       @RequestParam("text") String text) {
        timer.record(() -> siteService.save(id, title, text));
        return "redirect:http://localhost:8765/site";
    }

    /**
     * Файлы стилей
     *
     * @return
     */
    @GetMapping("/css/**")
    public String css() {
        return "../static/css/style.css";
    }

    /**
     * Скрипты JS
     *
     * @return
     */
    @GetMapping("/js/**")
    public String js() {
        return "../static/js/script1.js";
    }
}

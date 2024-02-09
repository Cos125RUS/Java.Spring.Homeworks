package com.example.controller;

import com.example.domain.Note;
import com.example.service.SiteService;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/site")
@RequiredArgsConstructor
public class SiteController {
    public final SiteService siteService;
    @GetMapping
    public String home(Model model) {
        List<Note> notes = siteService.getNoteList();
        model.addAttribute("notes", notes);
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "note";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable int id, Model model) {
        Note note = siteService.getNote(id);
        model.addAttribute("id", note.getId());
        model.addAttribute("text", note.getBody());
        model.addAttribute("title", note.getTitle());
        return "note";
    }

    @PostMapping("/push")
    public String push(@RequestParam("note_id") String id,
                       @RequestParam("note_title") String title,
                       @RequestParam("text") String text) {
        siteService.save(id, title, text);
        return "redirect:http://localhost:8765/site";
    }

    @GetMapping("/css/**")
    public String css() {
        return "../static/css/style.css";
    }

    @GetMapping("/js/**")
    public String js() {
        return "../static/js/script1.js";
    }
}

package com.example.controller;

import com.example.domain.Note;
import com.example.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/css/**")
    public String css() {
        return "style.css";
    }

    @GetMapping("/js/**")
    public String js() {
        return "script1.js";
    }
}

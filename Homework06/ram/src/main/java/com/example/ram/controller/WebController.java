package com.example.ram.controller;

import com.example.ram.config.Links;
import com.example.ram.domain.Characters;
import com.example.ram.service.ParserService;
import com.example.ram.service.ServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Контролёр запросов к серверу
 */
@Controller
@RequiredArgsConstructor
public class WebController {
    private final ServiceApi serviceApi;
    private final Links links;

    /**
     * главная страница
     * @return index.html
     */
    @GetMapping
    public String index() {
        return "redirect:/page/1";
    }

    /**
     * Переход по страницам
     * @param page страница
     * @param model модель страницы
     * @return выбранная страница
     */
    @GetMapping("/page/{page}")
    public String prev(@PathVariable int page, Model model) {
        Characters allCharacters = serviceApi.getAllCharacters(links.getCharacters()
                + "?page=" + page);
        String[] pages = ParserService.getPages(allCharacters, links.getPath());
        model.addAttribute("prev", pages[0]);
        model.addAttribute("next", pages[1]);
        model.addAttribute("charactersList", allCharacters.getResults());
        return "index";
    }
}

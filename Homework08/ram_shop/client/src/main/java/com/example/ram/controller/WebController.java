package com.example.ram.controller;

import com.example.ram.config.Links;
import com.example.ram.domain.Characters;
import com.example.ram.domain.Result;
import com.example.ram.service.ParserService;
import com.example.ram.service.PayService;
import com.example.ram.service.ServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Контролёр запросов к серверу
 */
@Controller
@RequiredArgsConstructor
public class WebController {
    private final ServiceApi serviceApi;
    private final ParserService parserService;
    private final Links links;
    private final PayService payService;
//    private final InMemoryUserDetailsManager userDetailsManager;

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
    public String page(@PathVariable int page, Model model) {
        Characters allCharacters = serviceApi.getAllCharacters(links.getCharacters()
                + "?page=" + page);
        String[] pages = parserService.getPages(allCharacters, links.getPath());
//        String username = userDetailsManager.loadUserByUsername("user").getUsername();
//        model.addAttribute("username", username);
        model.addAttribute("prev", pages[0]);
        model.addAttribute("next", pages[1]);
        model.addAttribute("charactersList", allCharacters.getResults());
        return "index";
    }

    /**
     * Страница персонажа
     * @param id идентификатор персонажа
     * @param model модель страницы
     * @return страница выбранного персонажа
     */
    @GetMapping("/character/{id}")
    public String character(@PathVariable int id, Model model) {
        Result result = serviceApi.getResult(links.getCharacters()
                + "/" + id);
        model.addAttribute("result", result);
        model.addAttribute("episodes", parserService.getEpisodes(result.getEpisode()));
        return "profile";
    }

    @GetMapping("/watch/{episode}")
    public String watch(@PathVariable String episode, Model model) {

        return "watch";
    }

    @PostMapping("/buy/{id}/{sum}/{episode}")
    public String buy(@PathVariable int id, @PathVariable int sum, @PathVariable String episode) {

        return "redirect:/watch/" + episode;
    }
}

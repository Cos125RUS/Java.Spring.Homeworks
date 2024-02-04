package com.example.ram.controller;

import com.example.ram.aspect.Logging;
import com.example.ram.config.Links;
import com.example.ram.config.Requisites;
import com.example.ram.domain.Characters;
import com.example.ram.domain.DownloadRequest;
import com.example.ram.domain.Result;
import com.example.ram.repository.UserRepository;
import com.example.ram.service.download.ProviderService;
import com.example.ram.service.parse.ParserService;
import com.example.ram.service.pay.PayService;
import com.example.ram.service.info.ServiceApi;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Контролёр запросов к серверу
 */
@Controller
@RequiredArgsConstructor
public class ClientController {
    private final ServiceApi serviceApi;
    private final ParserService parserService;
    private final Links links;
    private final PayService payService;
    private final ProviderService providerService;
    private final Requisites requisites;
    private final UserRepository userRepository;
//    private final InMemoryUserDetailsManager userDetailsManager;

    /**
     * главная страница
     *
     * @return index.html
     */
    @GetMapping
    public String index() {
        return "redirect:/page/1";
    }

    /**
     * Переход по страницам
     *
     * @param page  страница
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
     *
     * @param id    идентификатор персонажа
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
        model.addAttribute("episode", episode);
        return "watch";
    }

    @GetMapping("/buy/{username}/{episode}")
    @Logging
    public String buy(@PathVariable String username, @PathVariable String episode) {
        DownloadRequest downloadRequest = new DownloadRequest(
                UUID.randomUUID(), requisites.getProvider(), episode, LocalDateTime.now());
        if (providerService.download(downloadRequest, links.getProvider() + "get")) {
            if (payService.pay(userRepository.findByName(username).getId(), requisites.getPrice(),
                    links.getTransfer()))
                return "redirect:/watch/" + episode;
            else {
                providerService.refund(downloadRequest.getId(), links.getProvider() + "refund");
                return "redirect:/";
            }
        } else return "redirect:/";
    }

    @GetMapping("/confirm/{episode}")
    public String confirm(@PathVariable String episode, Model model) {
        model.addAttribute("episode", episode);
        return "confirm";
    }
}

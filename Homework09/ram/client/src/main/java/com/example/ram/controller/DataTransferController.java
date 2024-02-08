package com.example.ram.controller;

import com.example.ram.aspect.Logging;
import com.example.ram.config.Requisites;
import com.example.ram.domain.TransferRequest;
import com.example.ram.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.MalformedURLException;

/**
 * Рест контроллер
 */
@RestController
@RequiredArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RequestMapping("/ram")
public class DataTransferController {
    private final Requisites requisites;
    private final UserRepository userRepository;

    /**
     * Перенаправление на сайт банка с реквизитами в небезопасном виде
     * @param username
     * @param episode
     * @param attributes
     * @return
     */
    @GetMapping("/buy/{username}/{episode}")
    @Logging
    public RedirectView buy(@PathVariable String username, @PathVariable String episode,
                            RedirectAttributes attributes){
        attributes.addAttribute("senderAccountId", userRepository.findByName(username).getId());
        attributes.addAttribute("receiverAccountId", requisites.getBank());
        attributes.addAttribute("amount", requisites.getPrice());
        attributes.addAttribute("episode", episode);
        return new RedirectView ("http://localhost:8765/bank/confirm");
    }



}

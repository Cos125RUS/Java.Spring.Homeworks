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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class DataTransferController {
    private final Requisites requisites;
    private final UserRepository userRepository;

    @GetMapping("/buy/{username}/{episode}")
    @Logging
    public RedirectView buy(@PathVariable String username, @PathVariable String episode,
                            RedirectAttributes attributes){
        attributes.addAttribute("senderAccountId", userRepository.findByName(username).getId());
        attributes.addAttribute("receiverAccountId", requisites.getBank());
        attributes.addAttribute("amount", requisites.getPrice());
        attributes.addAttribute("episode", episode);
        return new RedirectView ("http://localhost:8081/confirm");
    }



}

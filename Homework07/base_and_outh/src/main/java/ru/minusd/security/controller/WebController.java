package ru.minusd.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.minusd.security.service.UserService;

@Configuration
@RequiredArgsConstructor
@RequestMapping("/web")
public class WebController {
    private final UserService service;

    @GetMapping("/home")
    public String home() {
        return "index";
    }

}

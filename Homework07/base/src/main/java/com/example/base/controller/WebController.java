package com.example.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/private-data")
    public String privateData() {
        return "private.html";
    }

    @GetMapping("/public-data")
    public String publicData() {
        return "public.html";
    }
}

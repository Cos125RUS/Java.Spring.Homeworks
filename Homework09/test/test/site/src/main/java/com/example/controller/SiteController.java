package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site1")
public class SiteController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("link", "/site1/page2");
        return "index";
    }

    @GetMapping("/page2")
    public String page2(Model model) {
        model.addAttribute("link", "/site2/page3");
        return "page2";
    }
}

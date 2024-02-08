package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site2")
public class SiteController {

//    @GetMapping
//    public String home(Model model) {
//        System.out.println(111);
//        model.addAttribute("link", "/");
//        return "page3";
//    }

    @GetMapping("/page3")
    public String page3(Model model) {
        model.addAttribute("link", "/site3/page4");
        return "page3";
    }
}

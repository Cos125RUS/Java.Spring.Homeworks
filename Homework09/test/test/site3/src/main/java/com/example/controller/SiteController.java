package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site3")
public class SiteController {

//    @GetMapping
//    public String home(Model model) {
//        System.out.println(111);
//        model.addAttribute("link", "/");
//        return "page3";
//    }

    @GetMapping("/page4")
    public String page4(Model model) {
        model.addAttribute("link", "/site1");
        return "page4";
    }
}

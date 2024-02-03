package com.example.base.controller;

import com.example.base.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/registration")
    public String registration() {
        return "registration.html";
    }

//    @PostMapping("/registration")
//    public String newMember(@RequestBody Member member) {
//        System.out.println("Member: " + member);
//        return "index.html";
//    }
}

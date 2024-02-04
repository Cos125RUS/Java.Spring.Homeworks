package com.example.ram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest")
public class RestController {


    @PostMapping("/buy/{id}/{sum}")
    public String buy(@PathVariable int id, @PathVariable int sum) {
        String path = "localhost:8081/buy/"+id+"/"+sum;
        return "redirect:/" + path;
    }
}

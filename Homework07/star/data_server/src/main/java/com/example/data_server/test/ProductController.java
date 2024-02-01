package com.example.data_server.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/messages")
    public List<Message> getProducts() {
        return Arrays.asList(
                new Message[]{new Message(1, "Hello World"),
                        new Message(2, "Hello ли?"),
                        new Message(3, "=)")});
    }
}

package com.example.site.model;


import lombok.Data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
public class Node {
    private String message;
    private String creationTime;

    public Node(String message) {
        this.message = message;
        creationTime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));
    }

}

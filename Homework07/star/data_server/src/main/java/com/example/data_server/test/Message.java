package com.example.data_server.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {
    private int id;
    private String text;
}

package com.example.site.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateService {
    public LocalDate getNow() {
        return LocalDate.now();
    }
}

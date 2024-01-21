package com.example.site.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Как же это скучно и утомительно=(
 */
@Service
public class DateService {
    /**
     * Текущее время
     * @return вчера за пять минут до полуночи
     */
    public LocalDate getNow() {
        return LocalDate.now();
    }
}

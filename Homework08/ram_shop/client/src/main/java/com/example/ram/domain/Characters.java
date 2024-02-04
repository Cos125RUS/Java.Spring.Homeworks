package com.example.ram.domain;

import lombok.Data;

import java.util.List;

/**
 * список персонажей
 */
@Data
public class Characters {
     Info info;
     List<Result> results;
}

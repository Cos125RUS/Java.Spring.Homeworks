package com.example.provider.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Запросы
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Request {
    private UUID id;
    private int clientId;
    private String video;
    private LocalDateTime time;
}

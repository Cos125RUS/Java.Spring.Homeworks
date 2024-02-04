package com.example.ram.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
public class DownloadRequest {
    private UUID id;
    private int clientId;
    private String video;
    private LocalDateTime time;
}

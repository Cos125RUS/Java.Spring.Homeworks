package com.example.homework05.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 500)
    private String description;
    @Column(nullable = false)
    private TaskStatus status;
    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creation;

    public Task() {
    }

    public Task(Task task) {
        this.description = task.getDescription();
        this.status = TaskStatus.NOT_STARTED;
        this.creation = LocalDateTime.now();
    }
}

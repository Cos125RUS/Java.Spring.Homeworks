package com.example.nodes.configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфиг сервиса заметок
 */
@Configuration
public class NoteConfiguration {
    @Autowired
    private MeterRegistry meterRegistry;

    /**
     * Счётчик заметок
     * @param meterRegistry
     * @return
     */
    @Bean
    public Counter counter(MeterRegistry meterRegistry) {
        return Counter.builder("note_counter")
                .description("Counter of notes in the repository")
                .register(meterRegistry);
    }
}

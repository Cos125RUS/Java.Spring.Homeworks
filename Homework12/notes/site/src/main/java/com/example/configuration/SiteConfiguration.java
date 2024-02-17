package com.example.configuration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SiteConfiguration {
    @Autowired
    private MeterRegistry meterRegistry;

    /**
     * Таймер для замера пользовательских запросов к сайту
     * @param meterRegistry
     * @return
     */
    @Bean
    public Timer timer(MeterRegistry meterRegistry) {
        return Timer.builder("user_process_timer")
                .description("Timer for processing user requests")
                .register(meterRegistry);
    }
}

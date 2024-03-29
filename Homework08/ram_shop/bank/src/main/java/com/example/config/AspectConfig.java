package com.example.config;

import com.example.aspect.TransferLogging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Конфигурация аспектов
 */
@Configuration
public class AspectConfig {
    /**
     * Логер для аспектов
     * @return
     */
    @Bean
    public TransferLogging aspect() {
        return new TransferLogging();
    }

    /**
     * Файловый логер
     * @return
     */
    @Bean
    public Logger noteLogger() {
        Logger logger = Logger.getLogger(TransferLogging.class.getName());
        FileHandler handler = null;
        try {
            handler = new FileHandler("bank/log/log.txt");
//            handler = new FileHandler("log/log.txt");
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (IOException e) {
            if (handler != null)
                handler.close();
            throw new RuntimeException(e);
        }
        return logger;
    }
}

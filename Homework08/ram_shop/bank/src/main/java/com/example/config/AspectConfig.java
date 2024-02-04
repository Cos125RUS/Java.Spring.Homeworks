package com.example.config;

import com.example.aspect.TransferLogging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Configuration
public class AspectConfig {
    @Bean
    public TransferLogging aspect() {
        return new TransferLogging();
    }

    @Bean
    public Logger noteLogger() {
        Logger logger = Logger.getLogger(TransferLogging.class.getName());
        FileHandler handler = null;
        try {
            handler = new FileHandler("log/log.txt");
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

package com.example.ram.config;

import com.example.ram.aspect.LoggingAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Configuration
@ComponentScan(basePackages = "com.example.ram")
@EnableAspectJAutoProxy
public class AspectConfig {
    @Bean
    public LoggingAspect aspect() {
        return new LoggingAspect();
    }

    @Bean
    public Logger noteLogger() {
        Logger logger = Logger.getLogger(LoggingAspect.class.getName());
        FileHandler handler = null;
        try {
            handler = new FileHandler("client/log/log.txt");
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

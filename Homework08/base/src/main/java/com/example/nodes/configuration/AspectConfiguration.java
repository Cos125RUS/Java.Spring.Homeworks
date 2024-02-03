package com.example.nodes.configuration;

import com.example.nodes.aspect.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Configuration
@ComponentScan(basePackages = "com.example")
@EnableAspectJAutoProxy
public class AspectConfiguration {
    @Bean
    public LoggingAspect aspect() {
        return new LoggingAspect();
    }

    @Bean
    public Logger noteLogger() {
        Logger logger = Logger.getLogger(LoggingAspect.class.getName());
        ConsoleHandler handler = new ConsoleHandler();
//        FileHandler handler = new FileHandler("log.txt");
        handler.setFormatter(new SimpleFormatter());
        logger.addHandler(handler);
        return logger;
    }
}

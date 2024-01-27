package com.example.ram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigApi {
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    };

    @Bean
    public HttpHeaders headers()
    {
        return new HttpHeaders();
    }
}

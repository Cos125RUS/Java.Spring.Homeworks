package com.example.provider.config;

import com.example.provider.model.Client;
import com.example.provider.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class ClientConfig {
    private final ClientRepository clientRepository;
    @Bean
    public Client getClient() throws URISyntaxException {
        return clientRepository.save(new Client(new URI("http://localhost:8080"),
                "Rick and Morty", 1000));
    }
}

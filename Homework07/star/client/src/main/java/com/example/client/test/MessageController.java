package com.example.client.test;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@RestController
@AllArgsConstructor
public class MessageController {

    private WebClient webClient;

    @GetMapping(value = "/messages-view")
    public List<Message> getMessages() {
        return webClient
                .get()
                .uri("http://127.0.0.1:18090/messages")
                .attributes(clientRegistrationId("messages-client-client-credentials"))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Message>>() {})
                .block();
    }
}

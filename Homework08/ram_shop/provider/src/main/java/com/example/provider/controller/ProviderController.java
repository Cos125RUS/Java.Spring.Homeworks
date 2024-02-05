package com.example.provider.controller;

import com.example.provider.model.Client;
import com.example.provider.model.Request;
import com.example.provider.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер провайдера загрузок
 */
@RestController
@RequiredArgsConstructor
public class ProviderController {
    private final ClientService clientService;

    @PostMapping("/get")
     public boolean get(@RequestBody Request request) {
        return clientService.get(request);
    }

    @PostMapping("/refund")
    public boolean refund(@RequestBody UUID requestId) {
        return clientService.refund(requestId);
    }

    @GetMapping("/clients")
    public List<Client> findAll() {
        return clientService.findAll();
    }
}

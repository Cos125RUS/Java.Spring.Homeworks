package com.example.provider.service;

import com.example.provider.model.Client;
import com.example.provider.model.Request;
import com.example.provider.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Клиентский сервис
 */
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;


    public boolean get(Request request) {
        Client client = clientRepository.findById(request.getClientId()).get();
        new Thread(() -> {
            client.setLimit(client.getLimit() - 1);
            clientRepository.save(client);
        }).start();
        return client.getLimit() > 0;
    }

    public boolean refund(UUID requestId) {
        System.out.println(requestId);
//TODO Добавить БД запросов, поиск по UUID и отмену загрузки пользователя
        return true;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}

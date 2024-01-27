package com.example.ram.service;

import com.example.ram.domain.Characters;
import com.example.ram.domain.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Реализация интерфейса для работы с апи Рика и Морти
 */
@Service
public class ServiceApiImpl implements ServiceApi{

    @Autowired
    private RestTemplate template;

    @Autowired
    private HttpHeaders headers;

    /**
     * Получить список персонажей
     * @param path путь на нужную страницу апи
     * @return список персонажей
     */
    @Override
    public Characters getAllCharacters(String path) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> responce = template.exchange(path, HttpMethod.GET,entity, Characters.class);

        return responce.getBody();
    }
}

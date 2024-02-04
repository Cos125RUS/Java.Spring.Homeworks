package com.example.ram.service;

import com.example.ram.domain.Characters;
import com.example.ram.domain.Episode;
import com.example.ram.domain.Info;
import com.example.ram.domain.Result;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Реализация интерфейса для работы с апи Рика и Морти
 */
@Service
@AllArgsConstructor
public class ServiceApiImpl implements ServiceApi {

    private RestTemplate template;
    private HttpHeaders headers;

    /**
     * Получить список персонажей
     *
     * @param path путь на нужную страницу апи
     * @return список персонажей
     */
    @Override
    public Characters getAllCharacters(String path) {
        ResponseEntity<Characters> response = template.exchange(path, HttpMethod.GET, getEntity(), Characters.class);
        return response.getBody();
    }

    /**
     * Получить информацию о персонаже
     *
     * @param path путь на нужную страницу апи
     * @return информация о персонаже
     */
    @Override
    public Result getResult(String path) {
        ResponseEntity<Result> response = template.exchange(path, HttpMethod.GET, getEntity(), Result.class);
        return response.getBody();
    }

    /**
     * Получить информацию об эпизодах
     * @param path ссылка на эпизод
     * @return список эпизодов
     */
    @Override
    public Episode getEpisode(String path) {
        ResponseEntity<Episode> response = template.exchange(path, HttpMethod.GET, getEntity(), Episode.class);
        return response.getBody();
    }

    /**
     * получить новую Entity
     *
     * @return новая entity
     */
    private HttpEntity<String> getEntity() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }
}

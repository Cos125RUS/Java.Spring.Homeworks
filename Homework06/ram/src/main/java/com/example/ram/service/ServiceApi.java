package com.example.ram.service;

import com.example.ram.domain.Characters;
import com.example.ram.domain.Episode;
import com.example.ram.domain.Result;

import java.util.List;

/**
 * интерфейс для работы с апи Рика и Морти
 */
public interface ServiceApi {
    Characters getAllCharacters(String path);
    Result getResult(String path);
    Episode getEpisode(String path);

}

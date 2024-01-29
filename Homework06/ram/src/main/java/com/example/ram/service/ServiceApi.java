package com.example.ram.service;

import com.example.ram.domain.Characters;
import com.example.ram.domain.Episode;
import com.example.ram.domain.Result;

import java.util.List;

/**
 * интерфейс для работы с апи Рика и Морти
 */
public interface ServiceApi {
    public Characters getAllCharacters(String path);
    public Result getResult(String path);
    public Episode getEpisode(String path);

}

package com.example.ram.service;

import com.example.ram.domain.Characters;

/**
 * интерфейс для работы с апи Рика и Морти
 */
public interface ServiceApi {
    public Characters getAllCharacters(String path);

}

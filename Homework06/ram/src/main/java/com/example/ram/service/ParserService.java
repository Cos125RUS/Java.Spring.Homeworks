package com.example.ram.service;

import com.example.ram.config.Links;
import com.example.ram.domain.Characters;
import com.example.ram.domain.Info;
import org.springframework.stereotype.Service;

/**
 * Преобразователь пути
 */
public class ParserService {
    /**
     * Создаёт ссылки на следующую и предыдущую страницы
     * @param allCharacters список персонажей
     * @param path ссылка на путь на сайте
     * @return массив ссылок
     */
    public static String[] getPages(Characters allCharacters, String path) {
        String[] pages = new String[2];
        Info info = allCharacters.getInfo();
        String prev = info.getPrev();
        String next = info.getNext();
        if (prev != null)
            pages[0] = path + prev.split("page=")[1];
        if (next != null)
            pages[1] = path + next.split("page=")[1];
        return pages;
    }
}

package com.example.ram.service;

import com.example.ram.config.Links;
import com.example.ram.domain.Characters;
import com.example.ram.domain.Episode;
import com.example.ram.domain.Info;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Преобразователь пути
 */
@Service
@AllArgsConstructor
public class ParserService {
    private ServiceApi serviceApi;


    /**
     * Создаёт ссылки на следующую и предыдущую страницы
     *
     * @param allCharacters список персонажей
     * @param path          ссылка на путь на сайте
     * @return массив ссылок
     */
    public String[] getPages(Characters allCharacters, String path) {
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

    /**
     * Получает данные об эпизодах из ссылок api
     *
     * @param urlEpisodes список ссылок на эпизоды
     * @return список информации об эпизодах
     */
    public List<Episode> getEpisodes(List<String> urlEpisodes) {
        List<Episode> episodes = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        urlEpisodes.forEach(url -> executorService.execute(() -> episodes.add(serviceApi.getEpisode(url))));
        executorService.shutdown();
        try {
            if (executorService.awaitTermination(1, TimeUnit.MINUTES))
                Collections.sort(episodes);
            return episodes;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

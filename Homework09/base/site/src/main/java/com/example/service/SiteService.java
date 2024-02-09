package com.example.service;

import com.example.domain.Note;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Внутренняя обработка запросов на сайт
 */
@Service
public class SiteService {

    /**
     * Получить список всех заметок
     * @return
     */
    public List<Note> getNoteList() {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = template.exchange("http://localhost:8765/notes", HttpMethod.GET, entity, List.class);
        return (List<Note>) response.getBody();
    }

    /**
     * Получить выбранную заметку
     * @param id
     * @return
     */
    public Note getNote(int id) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Note> response = template.exchange("http://localhost:8765/notes/" + id, HttpMethod.GET, entity, Note.class);
        return response.getBody();
    }

    /**
     * Сохранение заметки
     * @param id
     * @param title
     * @param text
     * @return
     */
    public Note save(String id, String title, String text) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String url = "http://localhost:8765/notes";
        HttpMethod method;
        Note note = new Note(title, text);
        if (!id.isEmpty()) {
            long noteId = Long.parseLong(id);
            note.setId(noteId);
            url += "/" + noteId;
            method = HttpMethod.PUT;
        } else {
            method = HttpMethod.POST;
        }
        HttpEntity<Note> entity = new HttpEntity<>(note, headers);
        ResponseEntity<Note> response = template.exchange(url, method, entity, Note.class);
        return response.getBody();
    }
}

package com.example.service;

import com.example.domain.Note;
import com.example.domain.NoteFactory;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Внутренняя обработка запросов на сайт
 */
@Service
@RequiredArgsConstructor
public class SiteService implements NoteUsingService {
    private final Timer timer;

    /**
     * Получить список всех заметок
     *
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
     *
     * @param id
     * @return
     */
    public Note getNote(int id) {
        AtomicReference<Note> note = null;
        timer.record(() -> {
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Note> response = template.exchange("http://localhost:8765/notes/" + id,
                    HttpMethod.GET, entity, Note.class);
            note.set(response.getBody());
        });
        return note.get();
    }

    /**
     * Сохранение заметки
     *
     * @param id
     * @param title
     * @param text
     * @return
     */
    public Note save(String id, String title, String text) {
        AtomicReference<Note> result = null;
        timer.record(() -> {
            RestTemplate template = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            String url = "http://localhost:8765/notes";
            HttpMethod method;
            Note note = NoteFactory.createNote(title, text);
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
            result.set(response.getBody());
        });
        return result.get();
    }
}

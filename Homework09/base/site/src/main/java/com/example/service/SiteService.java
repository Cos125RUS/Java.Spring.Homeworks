package com.example.service;

import ch.qos.logback.core.subst.Node;
import com.example.domain.Note;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SiteService {

    public List<Note> getNoteList() {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List> response = template.exchange("http://localhost:8765/notes", HttpMethod.GET, entity, List.class);
        return (List<Note>) response.getBody();
    }

    public Note getNote(int id) {
        RestTemplate template = null;
        HttpHeaders headers = null;
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Note> response = template.exchange("/notes/" + id, HttpMethod.GET, entity, Note.class);
        return response.getBody();
    }
}

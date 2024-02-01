package com.example.nodes.repository;

import com.example.nodes.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Интерфейс репозитория заметок
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * Поиск по id
     * @param id идентификатор заметки
     * @return опциональная заметка=)
     */
    Optional<Note> findById(Long id);
}

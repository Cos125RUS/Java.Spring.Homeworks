package com.example.nodes.service;

import com.example.nodes.domain.Note;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * Интерфейс интеграции
 */
@MessagingGateway(defaultRequestChannel = "textInputChanel")
public interface FileGateway {
    /**
     * Запись данных в канал интеграции
     * @param filename
     * @param note
     */
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, Note note);
}

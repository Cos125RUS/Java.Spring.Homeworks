package com.example.nodes.configuration;

import com.example.nodes.domain.Note;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * Конфигурация интеграции
 */
@Configuration
public class IntegrationConfig {
    /**
     * Входящий текстовый канал
     * @return
     */
    @Bean
    public MessageChannel textInputChanel() {
        return new DirectChannel();
    }

    /**
     * Канал записи в файл
     * @return
     */
    @Bean
    public MessageChannel fileWriterChanel() {
        return new DirectChannel();
    }

    /**
     * Трансформатор данных
     * @return
     */
    @Bean
    @Transformer(inputChannel = "textInputChanel", outputChannel = "fileWriterChanel")
    public GenericTransformer<Note, String> mainTransformer() {
        return note -> {
            StringBuilder sb = new StringBuilder();
            sb.append("note_id: ").append(note.getId()).append("\ntitle: ").append(note.getTitle())
                    .append("\ncreation_at: ").append(note.getCreation()).append("\n\n")
                    .append(note.getBody());
            return sb.toString();
        };
    }

    /**
     * Активатор записи в файл
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChanel")
    public FileWritingMessageHandler messageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("files"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        handler.setAutoCreateDirectory(true);
        handler.setCharset("UTF8");
        return handler;
    }
}

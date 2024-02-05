package com.example.ram.service.download;

import com.example.ram.domain.DownloadRequest;
import com.example.ram.domain.Episode;

import java.util.Map;
import java.util.UUID;

/**
 * Интерфейс сервиса загрузок
 */
public interface ProviderService {
    boolean download(DownloadRequest downloadRequest, String link);

    boolean refund(UUID id, String link);
}

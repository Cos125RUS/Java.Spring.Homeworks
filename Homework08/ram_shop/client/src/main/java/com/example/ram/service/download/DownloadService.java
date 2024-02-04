package com.example.ram.service.download;

import com.example.ram.config.Links;
import com.example.ram.config.Requisites;
import com.example.ram.domain.DownloadRequest;
import com.example.ram.domain.Episode;
import com.example.ram.domain.Transfer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DownloadService implements ProviderService{
    private RestTemplate template;

    @Override
    public boolean download(DownloadRequest downloadRequest, String link) {
        try {
            RequestEntity<DownloadRequest> entity = new RequestEntity<>(downloadRequest,
                    HttpMethod.POST, new URI(link));
            ResponseEntity<Boolean> response = template.exchange(entity, Boolean.class);
            return response.getBody();
        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
            return false;
        }
    }

    @Override
    public boolean refund(UUID id, String link) {
        try {
            RequestEntity<UUID> entity = new RequestEntity<>(id,
                    HttpMethod.POST, new URI(link));
            ResponseEntity<Boolean> response = template.exchange(entity, Boolean.class);
            return response.getBody();
        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
            return false;
        }
    }

}

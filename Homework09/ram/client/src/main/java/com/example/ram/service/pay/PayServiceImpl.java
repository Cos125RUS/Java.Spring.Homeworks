package com.example.ram.service.pay;

import com.example.ram.config.Requisites;
import com.example.ram.domain.TransferRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Платёжный сервис
 */
@AllArgsConstructor
@Service
public class PayServiceImpl implements PayService {
    private RestTemplate template;
    private Requisites requisites;

    @Override
    public boolean pay(int id, BigDecimal sum, String link) {
        try {
            RequestEntity<TransferRequest> entity = new RequestEntity<>(new TransferRequest(id, requisites.getBank(), sum),
                    HttpMethod.POST, new URI(link));
            ResponseEntity<Boolean> response = template.exchange(entity, Boolean.class);
            return response.getBody();
        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
            return false;
        }
    }
}

package com.example.ram.service;

import com.example.ram.domain.Characters;
import com.example.ram.domain.Transfer;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@AllArgsConstructor
@Service
public class PayServiceImpl implements PayService {
    private static final int BANK_REQUISITES = 2;
    private RestTemplate template;

    @Override
    public boolean pay(int id, BigDecimal sum, String link) {
        try {
            RequestEntity<Transfer> entity = new RequestEntity<>(new Transfer(id, BANK_REQUISITES, sum),
                    HttpMethod.POST, new URI(link));
            ResponseEntity<Boolean> response = template.exchange(entity, Boolean.class);
            return response.getBody();
        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
            return false;
        }
    }
}

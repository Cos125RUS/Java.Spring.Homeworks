package com.example.ram.service.pay;

import java.math.BigDecimal;

public interface PayService {
    boolean pay(int id, BigDecimal sum, String link);
}
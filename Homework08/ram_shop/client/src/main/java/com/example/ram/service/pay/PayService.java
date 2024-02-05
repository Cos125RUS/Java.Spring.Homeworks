package com.example.ram.service.pay;

import java.math.BigDecimal;

/**
 * Интерфейс сервиса оплаты
 */
public interface PayService {
    boolean pay(int id, BigDecimal sum, String link);
}

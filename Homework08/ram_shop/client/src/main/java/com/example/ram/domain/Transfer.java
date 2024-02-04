package com.example.ram.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Transfer {
    private long senderAccountId;
    private long receiverAccountId;
    private BigDecimal amount;
}

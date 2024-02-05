package com.example.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Параметры трансфера
 */
@Data
public class TransferRequest {

  private long senderAccountId;
  private long receiverAccountId;
  private BigDecimal amount;

}

package com.example.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Клиентские аккаунты
 */
@Data
public class Account {

  private long id;
  private String name;
  private BigDecimal amount;

}

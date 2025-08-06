package com.paymentchain.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

  private Long id;

  @Size(max = 255)
  private String reference;

  @Size(max = 255)
  private String iban;

  @NotNull private Double amount;

  @NotNull private Double fee;

  @Size(max = 255)
  private String description;

  @Size(max = 255)
  private String status;

  @NotNull
  @Size(max = 255)
  private String channel;
}

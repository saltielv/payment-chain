package com.paymentchain.customer.dto;

import lombok.Data;

@Data
public class CustomerDTO {
  private Long id;
  private String name;
  private String surname;
  private String code;
  private String iban;
  private String phone;
  private String address;
}

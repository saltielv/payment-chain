package com.paymentchain.customer.dto;

import java.util.List;
import lombok.Data;

@Data
public class CustomerFullResponseDTO {

  private Long id;
  private String name;
  private String surname;
  private String code;
  private String iban;
  private String phone;
  private String address;

  private List<ProductDTO> products;
  private List<?> transactions;
}

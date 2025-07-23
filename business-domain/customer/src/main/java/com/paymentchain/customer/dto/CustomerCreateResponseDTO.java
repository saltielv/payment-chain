package com.paymentchain.customer.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreateResponseDTO {
  private Long id;
  private String name;
  private String surname;
  private String code;
  private String iban;
  private String phone;
  private String address;

  private List<ProductDTO> products;
}

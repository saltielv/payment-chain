package com.paymentchain.customer.mapper;

import com.paymentchain.customer.dto.CustomerDTO;
import com.paymentchain.customer.model.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomerMapper {

  public static CustomerDTO toDTO(Customer entity) {
    return CustomerDTO.builder()
        .id(entity.getId())
        .name(entity.getName())
        .surname(entity.getSurname())
        .iban(entity.getIban())
        .code(entity.getCode())
        .phone(entity.getPhone())
        .address(entity.getAddress())
        .build();
  }

  public static Customer toEntity(CustomerDTO dto) {
    return Customer.builder()
        .id(dto.getId())
        .name(dto.getName())
        .surname(dto.getSurname())
        .iban(dto.getIban())
        .code(dto.getCode())
        .phone(dto.getPhone())
        .address(dto.getAddress())
        .build();
  }
}

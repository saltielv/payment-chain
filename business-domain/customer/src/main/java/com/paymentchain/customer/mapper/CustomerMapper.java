package com.paymentchain.customer.mapper;

import com.paymentchain.customer.dto.CustomerCreateRequestDTO;
import com.paymentchain.customer.dto.CustomerCreateResponseDTO;
import com.paymentchain.customer.dto.CustomerDTO;
import com.paymentchain.customer.dto.ProductDTO;
import com.paymentchain.customer.model.Customer;
import com.paymentchain.customer.model.CustomerProduct;
import java.util.List;
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
        .name(dto.getName())
        .surname(dto.getSurname())
        .iban(dto.getIban())
        .code(dto.getCode())
        .phone(dto.getPhone())
        .address(dto.getAddress())
        .build();
  }

  public static CustomerCreateResponseDTO toDTO(Customer entity, List<ProductDTO> products) {
    return CustomerCreateResponseDTO.builder()
        .id(entity.getId())
        .name(entity.getName())
        .surname(entity.getSurname())
        .iban(entity.getIban())
        .code(entity.getCode())
        .phone(entity.getPhone())
        .address(entity.getAddress())
        .products(products)
        .build();
  }

  public static Customer toEntity(CustomerCreateRequestDTO request, List<Long> productIds) {
    Customer entity =
        Customer.builder()
            .name(request.getName())
            .surname(request.getSurname())
            .iban(request.getIban())
            .code(request.getCode())
            .phone(request.getPhone())
            .address(request.getAddress())
            .build();

    List<CustomerProduct> customerProducts =
        CustomerProductMapper.toCustomerProductList(productIds);
    customerProducts.forEach(cp -> cp.setCustomer(entity)); // Inverted relationship
    entity.setCustomerProducts(customerProducts);

    return entity;
  }
}

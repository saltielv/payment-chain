package com.paymentchain.customer.mapper;

import com.paymentchain.customer.dto.CustomerCreateRequestDTO;
import com.paymentchain.customer.dto.CustomerCreateResponseDTO;
import com.paymentchain.customer.dto.CustomerDTO;
import com.paymentchain.customer.dto.ProductDTO;
import com.paymentchain.customer.model.Customer;
import com.paymentchain.customer.model.CustomerProduct;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public final class CustomerMapper {

  public CustomerDTO toDTO(final Customer entity, final CustomerDTO dto) {
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setSurname(entity.getSurname());
    dto.setIban(entity.getIban());
    dto.setCode(entity.getCode());
    dto.setPhone(entity.getPhone());
    dto.setAddress(entity.getAddress());
    return dto;
  }

  public Customer toEntity(final CustomerDTO dto, final Customer entity) {
    entity.setName(dto.getName());
    entity.setSurname(dto.getSurname());
    entity.setIban(dto.getIban());
    entity.setCode(dto.getCode());
    entity.setPhone(dto.getPhone());
    entity.setAddress(dto.getAddress());
    return entity;
  }

  public CustomerCreateResponseDTO toDTO(Customer entity, List<ProductDTO> products) {
    CustomerCreateResponseDTO dto = new CustomerCreateResponseDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setSurname(entity.getSurname());
    dto.setIban(entity.getIban());
    dto.setCode(entity.getCode());
    dto.setPhone(entity.getPhone());
    dto.setAddress(entity.getAddress());
    dto.setProducts(products);
    return dto;
  }

  public Customer toEntity(CustomerCreateRequestDTO request, List<Long> productIds) {
    Customer entity = new Customer();
    entity.setName(request.getName());
    entity.setSurname(request.getSurname());
    entity.setIban(request.getIban());
    entity.setCode(request.getCode());
    entity.setPhone(request.getPhone());
    entity.setAddress(request.getAddress());

    List<CustomerProduct> customerProducts =
        CustomerProductMapper.toCustomerProductList(productIds);
    customerProducts.forEach(cp -> cp.setCustomer(entity)); // Inverted relationship
    entity.setCustomerProducts(customerProducts);

    return entity;
  }
}

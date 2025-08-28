package com.paymentchain.customer.service;

import com.paymentchain.customer.client.ProductRestClientService;
import com.paymentchain.customer.common.exception.BusinessException;
import com.paymentchain.customer.common.exception.BusinessExceptionReason;
import com.paymentchain.customer.dto.CustomerCreateRequestDTO;
import com.paymentchain.customer.dto.CustomerCreateResponseDTO;
import com.paymentchain.customer.dto.CustomerDTO;
import com.paymentchain.customer.dto.ProductDTO;
import com.paymentchain.customer.mapper.CustomerMapper;
import com.paymentchain.customer.model.Customer;
import com.paymentchain.customer.repository.CustomerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final ProductRestClientService productRestClientService;
  private final CustomerMapper customerMapper;

  @Override
  public CustomerCreateResponseDTO createCustomer(CustomerCreateRequestDTO dto) {

    List<ProductDTO> foundProducts = productRestClientService.getProductsByIds(dto.getProductIds());
    List<Long> foundProductsIds = foundProducts.stream().map(ProductDTO::getId).toList();

    Customer saved = customerRepository.save(customerMapper.toEntity(dto, foundProductsIds));
    return customerMapper.toDTO(saved, foundProducts);
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {

    return customerRepository
        .findById(id)
        .map(customer -> customerMapper.toDTO(customer, new CustomerDTO()))
        .orElseThrow(() -> new BusinessException(BusinessExceptionReason.CUSTOMER_NOT_FOUND));
  }

  @Override
  public CustomerDTO updateCustomerById(Long id, CustomerDTO dto) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(BusinessExceptionReason.CUSTOMER_NOT_FOUND));

    // Updating database
    customer.setName(dto.getName());
    customer.setSurname(dto.getSurname());
    customer.setCode(dto.getCode());
    customer.setIban(dto.getIban());
    customer.setPhone(dto.getPhone());
    customer.setAddress(dto.getAddress());

    Customer updated = customerRepository.save(customer);
    return customerMapper.toDTO(updated, new CustomerDTO());
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll().stream()
        .map(customer -> customerMapper.toDTO(customer, new CustomerDTO()))
        .toList();
  }

  @Override
  public void deleteCustomerById(Long id) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(BusinessExceptionReason.CUSTOMER_NOT_FOUND));
    customerRepository.delete(customer);
  }
}

package com.paymentchain.customer.service;

import com.paymentchain.customer.dto.CustomerDTO;
import com.paymentchain.customer.mapper.CustomerMapper;
import com.paymentchain.customer.model.Customer;
import com.paymentchain.customer.repository.CustomerRepository;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public CustomerDTO createCustomer(CustomerDTO dto) {
    Customer saved = customerRepository.save(CustomerMapper.toEntity(dto));
    return CustomerMapper.toDTO(saved);
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {

    return customerRepository
        .findById(id)
        .map(CustomerMapper::toDTO)
        .orElseThrow(() -> new NoSuchElementException("Customer not found"));
  }

  @Override
  public CustomerDTO updateCustomerById(Long id, CustomerDTO dto) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("Customer not found"));

    // Updating database
    customer.setName(dto.getName());
    customer.setSurname(dto.getSurname());
    customer.setCode(dto.getCode());
    customer.setIban(dto.getIban());
    customer.setPhone(dto.getPhone());
    customer.setAddress(dto.getAddress());

    Customer updated = customerRepository.save(customer);
    return CustomerMapper.toDTO(updated);
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll().stream().map(CustomerMapper::toDTO).toList();
  }

  @Override
  public void deleteCustomerById(Long id) {
    Customer customer =
        customerRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("Customer not found"));
    customerRepository.delete(customer);
  }
}

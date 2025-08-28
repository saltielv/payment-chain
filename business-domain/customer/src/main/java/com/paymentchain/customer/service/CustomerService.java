package com.paymentchain.customer.service;

import com.paymentchain.customer.dto.CustomerCreateRequestDTO;
import com.paymentchain.customer.dto.CustomerCreateResponseDTO;
import com.paymentchain.customer.dto.CustomerDTO;
import com.paymentchain.customer.dto.CustomerFullResponseDTO;
import java.util.List;

public interface CustomerService {

  CustomerCreateResponseDTO createCustomer(CustomerCreateRequestDTO dto);

  CustomerDTO getCustomerById(Long id);

  CustomerDTO updateCustomerById(Long id, CustomerDTO dto);

  List<CustomerDTO> getAllCustomers();

  void deleteCustomerById(Long id);

  CustomerFullResponseDTO getCustomerByCode(String code);
}

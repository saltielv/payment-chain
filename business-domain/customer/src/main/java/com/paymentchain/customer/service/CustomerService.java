package com.paymentchain.customer.service;

import com.paymentchain.customer.model.Customer;
import java.util.List;

public interface CustomerService {

  Customer createCustomer(Customer customer);

  Customer getCustomerById(Long id);

  Customer updateCustomerById(Long id, Customer customer);

  List<Customer> getAllCustomers();

  void deleteCustomerById(Long id);
}

package com.paymentchain.customer.service;

import com.paymentchain.customer.model.Customer;
import com.paymentchain.customer.repository.CustomerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    super();
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer createCustomer(Customer customer) {
    return customerRepository.save(customer);
  }

  @Override
  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id).get();
  }

  @Override
  public Customer updateCustomerById(Long id, Customer customer) {
    Customer find = customerRepository.findById(id).get();
    if (find != null) {
      find.setName(customer.getName());
      find.setSurname(customer.getSurname());
      find.setCode(customer.getCode());
      find.setIban(customer.getIban());
      find.setPhone(customer.getPhone());
      find.setAddress(customer.getAddress());
    }
    return customerRepository.save(find);
  }

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public void deleteCustomerById(Long id) {
    customerRepository.deleteById(id);
  }
}

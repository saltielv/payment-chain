package com.paymentchain.customer.controller;

import com.paymentchain.customer.model.Customer;
import com.paymentchain.customer.service.CustomerService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  public final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    super();
    this.customerService = customerService;
  }

  @GetMapping("/{id}")
  public Customer getById(@PathVariable Long id) {
    return customerService.getCustomerById(id);
  }

  @GetMapping()
  public List<Customer> getAll() {
    return customerService.getAllCustomers();
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Customer customer) {
    Customer save = customerService.createCustomer(customer);
    return ResponseEntity.ok(save);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer customer) {
    Customer save = customerService.updateCustomerById(id, customer);
    return ResponseEntity.ok(save);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    customerService.deleteCustomerById(id);
    return ResponseEntity.ok().build();
  }
}

package com.paymentchain.customer.controller;

import com.paymentchain.customer.dto.CustomerDTO;
import com.paymentchain.customer.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CustomerController {

  public final CustomerService customerService;

  @GetMapping("/{id}")
  public CustomerDTO getById(@PathVariable Long id) {
    return customerService.getCustomerById(id);
  }

  @GetMapping()
  public List<CustomerDTO> getAll() {
    return customerService.getAllCustomers();
  }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody CustomerDTO dto) {
    CustomerDTO save = customerService.createCustomer(dto);
    return ResponseEntity.ok(save);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
    CustomerDTO save = customerService.updateCustomerById(id, dto);
    return ResponseEntity.ok(save);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    customerService.deleteCustomerById(id);
    return ResponseEntity.ok().build();
  }
}

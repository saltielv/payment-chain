package com.paymentchain.customer.controller;

import com.paymentchain.customer.dto.CustomerCreateRequestDTO;
import com.paymentchain.customer.dto.CustomerCreateResponseDTO;
import com.paymentchain.customer.dto.CustomerDTO;
import com.paymentchain.customer.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(customerService.getCustomerById(id));
  }

  @GetMapping()
  public ResponseEntity<List<CustomerDTO>> getAll() {
    return ResponseEntity.ok(customerService.getAllCustomers());
  }

  @PostMapping
  public ResponseEntity<CustomerCreateResponseDTO> create(
      @RequestBody CustomerCreateRequestDTO dto) {
    CustomerCreateResponseDTO save = customerService.createCustomer(dto);
    return new ResponseEntity<>(save, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerDTO dto) {
    CustomerDTO save = customerService.updateCustomerById(id, dto);
    return ResponseEntity.ok(save);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    customerService.deleteCustomerById(id);
    return ResponseEntity.noContent().build();
  }
}

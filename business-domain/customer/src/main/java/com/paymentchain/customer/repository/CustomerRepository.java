package com.paymentchain.customer.repository;

import com.paymentchain.customer.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  public Optional<Customer> findByCode(String code);
}

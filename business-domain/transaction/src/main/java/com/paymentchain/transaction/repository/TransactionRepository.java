package com.paymentchain.transaction.repository;

import com.paymentchain.transaction.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  public List<Transaction> findByIban(String iban);
}

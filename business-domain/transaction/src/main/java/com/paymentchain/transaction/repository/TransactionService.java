package com.paymentchain.transaction.repository;

import com.paymentchain.transaction.dto.TransactionDTO;
import java.util.List;

public interface TransactionService {

  List<TransactionDTO> findAll();

  TransactionDTO get(final Long id);

  TransactionDTO create(final TransactionDTO transactionDTO);

  TransactionDTO update(final Long id, final TransactionDTO transactionDTO);

  void delete(final Long id);
}

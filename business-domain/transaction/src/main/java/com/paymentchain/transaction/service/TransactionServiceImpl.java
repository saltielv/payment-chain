package com.paymentchain.transaction.service;

import com.paymentchain.transaction.common.exception.BusinessException;
import com.paymentchain.transaction.common.exception.BusinessExceptionReason;
import com.paymentchain.transaction.dto.TransactionDTO;
import com.paymentchain.transaction.mapper.TransactionMapper;
import com.paymentchain.transaction.model.Transaction;
import com.paymentchain.transaction.repository.TransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;

  private final TransactionMapper transactionMapper;

  @Override
  public List<TransactionDTO> findAll() {
    final List<Transaction> transactions = transactionRepository.findAll(Sort.by("id"));
    return transactions.stream()
        .map(transaction -> transactionMapper.mapToDTO(transaction, new TransactionDTO()))
        .toList();
  }

  @Override
  public List<TransactionDTO> findBy(String iban) {
    final List<Transaction> transactions = transactionRepository.findByIban(iban);
    return transactions.stream()
        .map(transaction -> transactionMapper.mapToDTO(transaction, new TransactionDTO()))
        .toList();
  }

  @Override
  public TransactionDTO get(final Long id) {
    return transactionRepository
        .findById(id)
        .map(transaction -> transactionMapper.mapToDTO(transaction, new TransactionDTO()))
        .orElseThrow(() -> new BusinessException(BusinessExceptionReason.TRANSACTION_NOT_FOUND));
  }

  @Override
  public TransactionDTO create(final TransactionDTO transactionDTO) {
    final Transaction transaction = new Transaction();
    transactionMapper.mapToEntity(transactionDTO, transaction);
    return transactionMapper.mapToDTO(
        transactionRepository.save(transaction), new TransactionDTO());
  }

  @Override
  public TransactionDTO update(final Long id, final TransactionDTO transactionDTO) {
    final Transaction transaction =
        transactionRepository
            .findById(id)
            .orElseThrow(
                () -> new BusinessException(BusinessExceptionReason.TRANSACTION_NOT_FOUND));
    transactionMapper.mapToEntity(transactionDTO, transaction);
    return transactionMapper.mapToDTO(
        transactionRepository.save(transaction), new TransactionDTO());
  }

  @Override
  public void delete(final Long id) {
    final Transaction transaction =
        transactionRepository
            .findById(id)
            .orElseThrow(
                () -> new BusinessException(BusinessExceptionReason.TRANSACTION_NOT_FOUND));
    transactionRepository.delete(transaction);
  }
}

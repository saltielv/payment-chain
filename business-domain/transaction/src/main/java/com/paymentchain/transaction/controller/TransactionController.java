package com.paymentchain.transaction.controller;

import com.paymentchain.transaction.dto.TransactionDTO;
import com.paymentchain.transaction.service.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

  private final TransactionServiceImpl transactionService;

  public TransactionController(final TransactionServiceImpl transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping
  public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
    return ResponseEntity.ok(transactionService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TransactionDTO> getTransaction(@PathVariable final Long id) {
    return ResponseEntity.ok(transactionService.get(id));
  }

  @GetMapping("/customer/transactions")
  public ResponseEntity<List<TransactionDTO>> getAllTransactionsByIban(@RequestParam String iban) {
    return ResponseEntity.ok(transactionService.findBy(iban));
  }

  @PostMapping
  @ApiResponse(responseCode = "201")
  public ResponseEntity<TransactionDTO> createTransaction(
      @RequestBody @Valid final TransactionDTO transactionDTO) {
    final TransactionDTO created = transactionService.create(transactionDTO);
    return new ResponseEntity<>(created, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TransactionDTO> updateTransaction(
      @PathVariable final Long id, @RequestBody @Valid final TransactionDTO transactionDTO) {
    final TransactionDTO updated = transactionService.update(id, transactionDTO);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/{id}")
  @ApiResponse(responseCode = "204")
  public ResponseEntity<Void> deleteTransaction(@PathVariable final Long id) {
    transactionService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

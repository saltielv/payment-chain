package com.paymentchain.customer.client;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class TransactionRestClientService {

  private final RestClient transactionRestClient;

  public List<?> getTransactionsByIban(String iban) {
    return transactionRestClient
        .get()
        .uri("/customer/transactions?iban={iban}", iban)
        .retrieve()
        .body(new ParameterizedTypeReference<>() {});
  }
}

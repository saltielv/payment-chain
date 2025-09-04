package com.paymentchain.customer.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class TransactionRestClientService {

  private final RestClient transactionRestClient;

  public TransactionRestClientService(
      @Qualifier("transactionRestClient") RestClient transactionRestClient) {
    this.transactionRestClient = transactionRestClient;
  }

  public List<?> getTransactionsByIban(String iban) {
    return transactionRestClient
        .get()
        .uri("/customer/transactions?iban={iban}", iban)
        .retrieve()
        .body(new ParameterizedTypeReference<>() {});
  }
}

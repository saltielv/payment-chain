package com.paymentchain.customer.client;

import com.paymentchain.customer.dto.ProductDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProductRestClientService {

  private final RestClient productRestClient;

  public ProductRestClientService(@Qualifier("productRestClient") RestClient productRestClient) {
    this.productRestClient = productRestClient;
  }

  public ProductDTO getProductById(Long id) {
    return productRestClient.get().uri("/{id}", id).retrieve().body(ProductDTO.class);
  }

  public List<ProductDTO> getProductsByIds(List<Long> productIds) {
    return productRestClient
        .post()
        .uri("/batch")
        .body(productIds)
        .retrieve()
        .body(new ParameterizedTypeReference<>() {});
  }
}

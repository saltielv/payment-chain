package com.paymentchain.customer.client;

import com.paymentchain.customer.dto.ProductDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ProductRestClientService {

  private final RestClient productRestClient;

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

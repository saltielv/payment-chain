package com.paymentchain.customer.mapper;

import com.paymentchain.customer.model.CustomerProduct;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerProductMapper {

  public static List<CustomerProduct> toCustomerProductList(List<Long> productIds) {
    return productIds.stream()
        .map(
            id -> {
              CustomerProduct customer = new CustomerProduct();
              customer.setProductId(id);
              return customer;
            })
        .toList();
  }
}

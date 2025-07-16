package com.paymentchain.product.mapper;

import com.paymentchain.product.dto.ProductDTO;
import com.paymentchain.product.model.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

  public static ProductDTO toDTO(Product entity) {
    return ProductDTO.builder()
        .id(entity.getId())
        .code(entity.getCode())
        .name(entity.getName())
        .build();
  }

  public static Product toEntity(ProductDTO dto) {
    return Product.builder().code(dto.getCode()).name(dto.getName()).build();
  }
}

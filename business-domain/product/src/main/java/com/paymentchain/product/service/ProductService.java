package com.paymentchain.product.service;

import com.paymentchain.product.dto.ProductDTO;
import java.util.List;

public interface ProductService {

  ProductDTO createProduct(ProductDTO dto);

  ProductDTO getProductById(Long id);

  ProductDTO updateProductById(Long id, ProductDTO dto);

  List<ProductDTO> getAllProducts();

  void deleteProductById(Long id);

  List<ProductDTO> findByIds(List<Long> ids);
}

package com.paymentchain.product.service;

import com.paymentchain.product.dto.ProductDTO;
import java.util.List;

public interface ProductService {

  ProductDTO createProduct(ProductDTO product);

  ProductDTO getProductById(Long id);

  ProductDTO updateProductById(Long id, ProductDTO product);

  List<ProductDTO> getAllProducts();

  void deleteProductById(Long id); 
  
  
}

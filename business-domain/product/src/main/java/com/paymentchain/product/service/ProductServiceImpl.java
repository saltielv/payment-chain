package com.paymentchain.product.service;

import com.paymentchain.product.common.exception.BusinessException;
import com.paymentchain.product.common.exception.BusinessExceptionReason;
import com.paymentchain.product.dto.ProductDTO;
import com.paymentchain.product.mapper.ProductMapper;
import com.paymentchain.product.model.Product;
import com.paymentchain.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public ProductDTO createProduct(ProductDTO dto) {
    Product saved = productRepository.save(ProductMapper.toEntity(dto));
    return ProductMapper.toDTO(saved);
  }

  @Override
  public ProductDTO getProductById(Long id) {
    return productRepository
        .findById(id)
        .map(ProductMapper::toDTO)
        .orElseThrow(() -> new BusinessException(BusinessExceptionReason.PRODUCT_NOT_FOUND));
  }

  @Override
  public ProductDTO updateProductById(Long id, ProductDTO dto) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(BusinessExceptionReason.PRODUCT_NOT_FOUND));
    product.setCode(dto.getCode());
    product.setName(dto.getName());
    Product saved = productRepository.save(product);
    return ProductMapper.toDTO(saved);
  }

  @Override
  public List<ProductDTO> getAllProducts() {
    return productRepository.findAll().stream().map(ProductMapper::toDTO).toList();
  }

  @Override
  public void deleteProductById(Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new BusinessException(BusinessExceptionReason.PRODUCT_NOT_FOUND));
    productRepository.delete(product);
  }

  @Override
  public List<ProductDTO> findByIds(List<Long> ids) {
    List<Product> products = productRepository.findAllById(ids);
    return products.stream().map(ProductMapper::toDTO).toList();
  }
}

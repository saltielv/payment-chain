package com.paymentchain.product.controller;

import com.paymentchain.product.dto.ProductDTO;
import com.paymentchain.product.service.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

  public final ProductService productService;

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

  @GetMapping()
  public ResponseEntity<List<ProductDTO>> getAll() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @PostMapping
  @ApiResponse(responseCode = "201")
  public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
    ProductDTO save = productService.createProduct(dto);
    return new ResponseEntity<>(save, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
    ProductDTO save = productService.updateProductById(id, dto);
    return ResponseEntity.ok(save);
  }

  @DeleteMapping("/{id}")
  @ApiResponse(responseCode = "204")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    productService.deleteProductById(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/batch")
  public ResponseEntity<List<ProductDTO>> getProductsByIds(@RequestBody List<Long> productIds) {
    List<ProductDTO> products = productService.findByIds(productIds);
    return ResponseEntity.ok(products);
  }
}

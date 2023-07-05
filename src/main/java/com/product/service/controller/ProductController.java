package com.product.service.controller;

import com.product.service.dto.ProductDTO;
import com.product.service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class ProductController {

    final ProductService productService;

    ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable final Long id) {
        return ResponseEntity.ok(this.productService.getById(id));
    }

    @DeleteMapping(value = "/products/{id}")
    public void deleteProductById(@PathVariable final Long id) {
        this.productService.deleteProductById(id);
    }

    @PatchMapping(value = "/products")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody final ProductDTO productDTO) {
        return ResponseEntity.ok(this.productService.updateProduct(productDTO));
    }

    @PostMapping(value = "/products")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody final ProductDTO product) {
        return ResponseEntity.ok(this.productService.saveProduct(product));
    }

}


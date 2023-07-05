package com.product.service.service;

import com.product.service.dto.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductDTO getById(Long productId);
    ProductDTO saveProduct(ProductDTO productDTO);
    void deleteProductById(Long productId);
    ProductDTO updateProduct(ProductDTO productDTO);
}

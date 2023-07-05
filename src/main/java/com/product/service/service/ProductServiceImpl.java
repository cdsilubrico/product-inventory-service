package com.product.service.service;

import com.product.service.dto.ProductDTO;
import com.product.service.exception.specific.DuplicateEntry;
import com.product.service.exception.specific.NoRecordFound;
import com.product.service.model.Product;
import com.product.service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.product.service.constant.Constant.DUPLICATE_PRODUCT;
import static com.product.service.constant.Constant.NO_RECORD_FOUND;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO getById(Long productId) {
        log.info("GET BY ID" + productId);
        return new ProductDTO(productRepository.findById(productId)
                .orElseThrow(() -> new NoRecordFound(NO_RECORD_FOUND)));
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {

        final Product product = new Product(productDTO);

        productRepository.findByName(productDTO.getName())
                .ifPresentOrElse(retrievedProduct -> {
                    throw new DuplicateEntry(DUPLICATE_PRODUCT);
                }, () -> productRepository.save(product));

        return new ProductDTO(product);
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.findById(productId)
                .ifPresentOrElse(productRepository::delete,
                        () -> {
                            throw new NoRecordFound(NO_RECORD_FOUND);
                        });
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        return null;
    }
}

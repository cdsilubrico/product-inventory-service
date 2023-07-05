package com.product.service.service;

import com.product.service.dto.ProductDTO;
import com.product.service.enums.ProductType;
import com.product.service.exception.specific.DuplicateEntry;
import com.product.service.exception.specific.NoRecordFound;
import com.product.service.model.Product;
import com.product.service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        log.info("GET BY ID " + productId);

        return new ProductDTO(productRepository.findById(productId)
                .orElseThrow(() -> new NoRecordFound(NO_RECORD_FOUND)));
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {

        log.info("SAVE PRODUCT " + productDTO.toString());

        final Product product = new Product(productDTO);

        productRepository.findByName(productDTO.getName())
                .ifPresentOrElse(retrievedProduct -> {
                    throw new DuplicateEntry(DUPLICATE_PRODUCT);
                }, () -> productRepository.save(product));

        return new ProductDTO(product);
    }

    @Override
    public void deleteProductById(Long productId) {

        log.info("DELETE BY ID " + productId);

        productRepository.findById(productId)
                .ifPresentOrElse(productRepository::delete,
                        () -> {
                            throw new NoRecordFound(NO_RECORD_FOUND);
                        });
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {

        log.info("UPDATE PRODUCT " + productDTO.toString());

        Product product = productRepository.findById(productDTO.getProductId())
                .orElseThrow(() -> new NoRecordFound(NO_RECORD_FOUND));

        return validateProductDetails(productDTO, product);
    }

    private ProductDTO validateProductDetails(ProductDTO newProductDTO, Product currentProduct) {

        Optional<String> nameOpt = Optional.ofNullable(newProductDTO.getName());
        nameOpt.ifPresent(name -> productRepository.findByName(name)
                .ifPresentOrElse(value -> {
                    throw new DuplicateEntry(DUPLICATE_PRODUCT);
                }, () ->
                        currentProduct.setName(name)));

        Optional<String> descriptionOpt = Optional.ofNullable(newProductDTO.getName());
        descriptionOpt.ifPresent(currentProduct::setDescription);

        Optional<ProductType> productTypeOpt = Optional.ofNullable(newProductDTO.getProductType());
        productTypeOpt.ifPresent(currentProduct::setProductType);

        Optional<Long> quantityOpt = Optional.ofNullable(newProductDTO.getQuantity());
        quantityOpt.ifPresent(currentProduct::setQuantity);

        Optional<Double> priceOpt = Optional.ofNullable(newProductDTO.getPrice());
        priceOpt.ifPresent(currentProduct::setPrice);

        return new ProductDTO(productRepository.save(currentProduct));
    }
}

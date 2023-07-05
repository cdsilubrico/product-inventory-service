package com.product.service.service;

import com.product.service.dto.ProductDTO;
import com.product.service.enums.ProductType;
import com.product.service.exception.specific.NoRecordFound;
import com.product.service.model.Product;
import com.product.service.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.product.service.constant.Constant.NO_RECORD_FOUND;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    public void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProduct() {
        ProductDTO productDTO = new ProductDTO();

        Product product = new Product();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO result = productServiceImpl.saveProduct(productDTO);

        verify(productRepository, times(1)).save(any(Product.class));

        assertNotNull(result);
    }

    @Test
    void getById() {
        Product product = new Product();
        product.setProductId(10L);
        product.setName("Test Name");
        product.setDescription("Test Description");
        product.setProductType(ProductType.APPLIANCE);
        product.setQuantity(2L);
        product.setPrice(21.44);

        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));

        ProductDTO expectedProduct = productServiceImpl.getById(product.getProductId());

        verify(productRepository, times(1)).findById(10L);

        Assertions.assertNotNull(expectedProduct);
        Assertions.assertEquals(10L, expectedProduct.getProductId());
        Assertions.assertEquals("Test Name", expectedProduct.getName());
        Assertions.assertEquals("Test Description", expectedProduct.getDescription());
        Assertions.assertEquals(ProductType.APPLIANCE, expectedProduct.getProductType());
        Assertions.assertEquals(2L, expectedProduct.getQuantity());
        Assertions.assertEquals(21.44, expectedProduct.getPrice());
    }

    @Test
    void givenAnInvalidIdShouldThrowNoRecordFoundOnDelete() {
        Long id = 10L;

        doThrow(new NoRecordFound(NO_RECORD_FOUND)).when(productRepository).deleteById(id);

        assertThrows(NoRecordFound.class, () -> productServiceImpl.deleteProductById(id));
    }

    @Test
    void givenAnInvalidProductIdShouldThrowNoRecordFoundOnUpdate() {
        Long productId = 10L;

        ProductDTO newProductDTO = new ProductDTO();
        newProductDTO.setProductId(productId);

        when(productRepository.save(any(Product.class))).thenThrow(new NoRecordFound(NO_RECORD_FOUND));

        Assertions.assertThrows(NoRecordFound.class, () -> productServiceImpl.updateProduct(newProductDTO));
    }
}
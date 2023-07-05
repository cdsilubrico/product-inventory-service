package com.product.service.dto;

import com.product.service.enums.ProductType;
import com.product.service.model.Product;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotEmpty(message = "Description can't be empty")
    private String description;
    @NotEmpty(message = "ProductType can't be empty")
    private ProductType productType;
    @NotEmpty(message = "Quantity can't be empty")
    private Long quantity;
    @NotEmpty(message = "Price can't be empty")
    private Double price;

    public ProductDTO(final Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.productType = product.getProductType();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }
}

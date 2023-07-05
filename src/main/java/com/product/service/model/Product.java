package com.product.service.model;

import com.product.service.dto.ProductDTO;
import com.product.service.enums.ProductType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "product_type")
    private ProductType productType;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "price")
    private Double price;

    public Product(ProductDTO productDTO) {
        this.name = productDTO.getName();
        this.description = productDTO.getDescription();
        this.productType = productDTO.getProductType();
        this.quantity = productDTO.getQuantity();
        this.price = productDTO.getPrice();
    }
}

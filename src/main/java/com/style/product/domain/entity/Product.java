package com.style.product.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.style.brand.domain.entity.Brand;
import com.style.common.domain.entity.BaseEntity;
import com.style.product.domain.ProductCategory;
import com.style.product.presentation.request.UpdateProductRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    @JsonIgnore
    private Brand brand;

    @Column(nullable = false)
    private ProductCategory category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    @Builder
    public Product(
            final Brand brand,
            final ProductCategory category,
            final String name,
            final BigDecimal price,
            final Integer quantity
    ) {
        this.brand = brand;
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void update(final UpdateProductRequest request) {
        if (request.isCategoryUpdate()) {
            this.setCategory(request.getCategory());
        }

        if (request.isNameUpdate()) {
            this.setName(request.getName());
        }

        if (request.isPriceUpdate()) {
            this.setPrice(request.getPrice());
        }

        if (request.isQuantityUpdate()) {
            this.setQuantity(request.getQuantity());
        }
    }

}

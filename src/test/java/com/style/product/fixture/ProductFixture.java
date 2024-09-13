package com.style.product.fixture;

import com.style.brand.domain.entity.Brand;
import com.style.product.domain.ProductCategory;
import com.style.product.domain.entity.Product;
import com.style.product.presentation.request.CreateProductRequest;
import com.style.product.presentation.request.UpdateProductRequest;

import java.math.BigDecimal;
import java.util.List;

public class ProductFixture {

    public static final String FIRST_PRODUCT_NAME = "Product1";
    public static final String SECOND_PRODUCT_NAME = "Product2";

    public static Product getMockProduct(Brand brand) {
        return Product.builder()
                .brand(Brand.builder().build())
                .category(ProductCategory.OUTER)
                .name("outer")
                .price(BigDecimal.valueOf(100))
                .quantity(10)
                .build();
    }

    public static List<Product> getMockProducts() {
        return List.of(
                Product.builder().name(FIRST_PRODUCT_NAME).build(),
                Product.builder().name(SECOND_PRODUCT_NAME).build()
        );
    }

    public static CreateProductRequest getCreateProductRequest(
            Long brandId,
            ProductCategory category,
            String name,
            BigDecimal price,
            Integer quantity
    ) {
        return new CreateProductRequest(brandId, category, name, price, quantity);
    }

    public static UpdateProductRequest getUpdateProductRequest(
            final ProductCategory category,
            final String name,
            final BigDecimal price,
            final Integer quantity
     ) {
        return new UpdateProductRequest(category, name, price, quantity);
    }

}

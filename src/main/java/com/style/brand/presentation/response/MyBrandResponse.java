package com.style.brand.presentation.response;

import com.style.product.domain.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Getter
@ToString
public class MyBrandResponse {

    private final String name;
    private final Long licenseNumber;
    private final Long phoneNumber;
    private final List<Product> products;

    @Builder
    public MyBrandResponse(
            final String name,
            final Long licenseNumber,
            final Long phoneNumber,
            final Set<Product> products
    ) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.phoneNumber = phoneNumber;
        this.products = convertProducts(products);
    }

    private List<Product> convertProducts(final Set<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(Product::getId))
                .toList();
    }

}

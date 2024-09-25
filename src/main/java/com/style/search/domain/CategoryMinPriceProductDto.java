package com.style.search.domain;

import com.style.product.domain.ProductCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(of = {"category", "price"})
@ToString
public class CategoryMinPriceProductDto {

    private final ProductCategory category;
    private final Long brandId;
    private final BigDecimal price;

    public CategoryMinPriceProductDto(
            final ProductCategory category,
            final Long brandId,
            final BigDecimal price
    ) {
        this.category = category;
        this.brandId = brandId;
        this.price = price;
    }

}

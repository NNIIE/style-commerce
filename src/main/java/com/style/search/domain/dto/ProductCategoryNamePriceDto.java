package com.style.search.domain.dto;

import com.style.product.domain.ProductCategory;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public record ProductCategoryNamePriceDto(ProductCategory category, String name,
                                          BigDecimal price) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

}

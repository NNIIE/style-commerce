package com.style.order.domain;

import com.style.product.domain.ProductCategory;

import java.math.BigDecimal;

public record SearchOrderProduct(
        Long orderItemId, Long productId,
        ProductCategory category,
        String name,
        BigDecimal price,
        Integer quantity) {
}

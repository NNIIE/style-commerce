package com.style.search.domain;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
public class CategoryMinPriceAggregate {

    private final BigDecimal totalPrice;
    private final List<CategoryMinPriceProductDto> products;

    public CategoryMinPriceAggregate(final List<CategoryMinPriceProductDto> products) {
        this.totalPrice = calculateTotalPrice(products);
        this.products = products;
    }

    // totalPrice 에는 카테고리와 가격이 같은 제품들을 중복 제거 해서 노출
    private BigDecimal calculateTotalPrice(final List<CategoryMinPriceProductDto> products) {
        return products.stream()
                .distinct()
                .map(CategoryMinPriceProductDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

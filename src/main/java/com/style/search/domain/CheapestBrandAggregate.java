package com.style.search.domain;

import com.style.product.domain.entity.Product;
import com.style.search.domain.dto.BrandTotalPriceDto;
import com.style.search.domain.dto.ProductCategoryNamePriceDto;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@ToString
public class CheapestBrandAggregate implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Long brandId;
    private final List<ProductCategoryNamePriceDto> products;
    private final BigDecimal totalPrice;

    public CheapestBrandAggregate(
            final BrandTotalPriceDto brandTotalPriceDto,
            final List<Product> products
    ) {
        this.brandId = brandTotalPriceDto.brandId();
        this.products = mapToProductCategoryNamePriceDtos(products);
        this.totalPrice = brandTotalPriceDto.totalPrice();
    }

    private List<ProductCategoryNamePriceDto> mapToProductCategoryNamePriceDtos(final List<Product> products) {
        return products.stream()
                .map(product -> new ProductCategoryNamePriceDto(
                        product.getCategory(),
                        product.getName(),
                        product.getPrice()))
                .toList();
    }

}

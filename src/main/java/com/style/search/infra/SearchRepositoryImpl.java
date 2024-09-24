package com.style.search.infra;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.style.product.domain.ProductCategory;
import com.style.product.domain.entity.Product;
import com.style.search.presentation.request.SearchProductsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.style.product.domain.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Product> findProductsByConditions(final SearchProductsRequest request) {
        return jpaQueryFactory.selectFrom(product)
                .where(
                        toProductName(request.getProductName()),
                        eqBrandId(request.getBrandId()),
                        eqCategory(request.getCategory())
                )
                .fetch();
    }

    private BooleanExpression toProductName(final String productName) {
        return Optional.ofNullable(productName)
                .filter(StringUtils::hasText)
                .map(product.name::containsIgnoreCase)
                .orElse(null);
    }

    private BooleanExpression eqBrandId(final Long brandId) {
        return Optional.ofNullable(brandId)
                .map(product.brand.id::eq)
                .orElse(null);
    }

    private BooleanExpression eqCategory(final ProductCategory category) {
        return Optional.ofNullable(category)
                .map(product.category::eq)
                .orElse(null);
    }

}

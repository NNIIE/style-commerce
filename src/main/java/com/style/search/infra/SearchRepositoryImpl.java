package com.style.search.infra;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.style.common.domain.QueryDslConditionBuilder;
import com.style.product.domain.entity.Product;
import com.style.product.domain.entity.QProduct;
import com.style.search.domain.dto.BrandTotalPriceDto;
import com.style.search.domain.dto.CategoryMinPriceProductDto;
import com.style.search.presentation.request.SearchProductsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.style.product.domain.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepositoryCustom, QueryDslConditionBuilder {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Product> findProductsByConditions(final SearchProductsRequest request, final Pageable pageable) {
        List<Product> products = jpaQueryFactory
                .select(product)
                .from(product)
                .where(
                        andLikeIgnoreCase(product.name, request.getProductName()),
                        andEquals(product.brand.id, request.getBrandId()),
                        andEquals(product.category, request.getCategory())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(product.count())
                .from(product)
                .where(
                        andLikeIgnoreCase(product.name, request.getProductName()),
                        andEquals(product.brand.id, request.getBrandId()),
                        andEquals(product.category, request.getCategory())
                );

        return PageableExecutionUtils.getPage(products, pageable, countQuery::fetchOne);
    }

    @Override
    public List<CategoryMinPriceProductDto> findLowestProductsByCategoryAndTotalPrice() {
        QProduct productSub = new QProduct("productSub");

        return jpaQueryFactory
                .select(Projections.constructor(CategoryMinPriceProductDto.class,
                        product.category,
                        product.brand.id,
                        product.price))
                .from(product)
                .where(product.price.eq(
                        JPAExpressions
                                .select(productSub.price.min())
                                .from(productSub)
                                .where(productSub.category.eq(product.category))
                                .groupBy(productSub.category)
                ))
                .fetch();
    }

    @Override
    public BrandTotalPriceDto findLowestBrandAndTotalPrice() {
        return jpaQueryFactory
                .select(Projections.constructor(BrandTotalPriceDto.class,
                        product.brand.id.as("brandId"),
                        product.price.sum().as("totalPrice")))
                .from(product)
                .groupBy(product.brand.id)
                .orderBy(product.price.sum().asc())
                .limit(1)
                .fetchOne();
    }

}

package com.style.search.infra;

import com.style.product.domain.entity.Product;
import com.style.search.domain.CategoryMinPriceProductDto;
import com.style.search.presentation.request.SearchProductsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchRepositoryCustom {

    Page<Product> findProductsByConditions(SearchProductsRequest request, Pageable pageable);

    List<CategoryMinPriceProductDto> findLowestProductsByCategoryAndTotalPrice();

}

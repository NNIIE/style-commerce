package com.style.search.infra;

import com.style.product.domain.entity.Product;
import com.style.search.presentation.request.SearchProductsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchRepositoryCustom {

    Page<Product> findProductsByConditions(SearchProductsRequest request, Pageable pageable);

}

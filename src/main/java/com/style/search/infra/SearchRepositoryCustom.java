package com.style.search.infra;

import com.style.product.domain.entity.Product;
import com.style.search.presentation.request.SearchProductsRequest;

import java.util.List;

public interface SearchRepositoryCustom {

    List<Product> findProductsByConditions(SearchProductsRequest request);

}

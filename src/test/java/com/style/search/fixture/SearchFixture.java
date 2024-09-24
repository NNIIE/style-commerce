package com.style.search.fixture;

import com.style.product.domain.ProductCategory;
import com.style.search.presentation.request.SearchProductsRequest;

public class SearchFixture {

    public static SearchProductsRequest getSearchProductsRequest(String productName, Long brandId, ProductCategory category) {
        SearchProductsRequest request = new SearchProductsRequest();
        request.setProductName(productName);
        request.setBrandId(brandId);
        request.setCategory(category);

        return request;
    }

}

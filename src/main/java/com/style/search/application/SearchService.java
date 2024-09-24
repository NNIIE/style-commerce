package com.style.search.application;

import com.style.product.domain.entity.Product;
import com.style.search.infra.SearchRepositoryImpl;
import com.style.search.presentation.request.SearchProductsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepositoryImpl searchRepository;

    @Transactional(readOnly = true)
    public List<Product> searchProducts(final SearchProductsRequest request) {
        return searchRepository.findProductsByConditions(request);
    }

}

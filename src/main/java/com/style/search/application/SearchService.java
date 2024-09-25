package com.style.search.application;

import com.style.common.domain.PagedResponse;
import com.style.product.domain.entity.Product;
import com.style.search.infra.SearchRepositoryImpl;
import com.style.search.presentation.request.SearchProductsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepositoryImpl searchRepository;

    @Transactional(readOnly = true)
    public PagedResponse<Product> searchProducts(final SearchProductsRequest request, final Pageable pageable) {
        final Page<Product> products = searchRepository.findProductsByConditions(request, pageable);

        return new PagedResponse<>(
                products.getContent(),
                products.getTotalPages(),
                products.getTotalElements(),
                products.getNumber(),
                products.getSize()
        );
    }

}

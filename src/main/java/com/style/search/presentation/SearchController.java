package com.style.search.presentation;

import com.style.common.domain.PagedResponse;
import com.style.common.domain.SessionMember;
import com.style.common.exception.request.RequestException;
import com.style.common.exception.request.RequestExceptionCode;
import com.style.member.domain.CurrentMember;
import com.style.product.domain.entity.Product;
import com.style.search.application.SearchService;
import com.style.search.presentation.request.SearchProductsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Search")
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 검색::조건별")
    public PagedResponse<Product> searchProducts(
            @Valid @ModelAttribute final SearchProductsRequest request,
            @PageableDefault Pageable pageable,
            @Parameter(hidden = true) @CurrentMember final SessionMember member
    ) {
        if (request.hasNoSearchCriteria()) {
            throw new RequestException(RequestExceptionCode.NO_SEARCH_CRITERIA);
        }
        return searchService.searchProducts(request, pageable);
    }

}

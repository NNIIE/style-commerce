package com.style.search.presentation;

import com.style.common.domain.SessionMember;
import com.style.member.domain.CurrentMember;
import com.style.product.domain.entity.Product;
import com.style.search.application.SearchService;
import com.style.search.presentation.request.SearchProductsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Search")
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품 검색::조건별")
    public List<Product> searchProducts(
            @Valid @ModelAttribute final SearchProductsRequest request,
            @Parameter(hidden = true) @CurrentMember final SessionMember member
    ) {
        if (request.isRequestNull()) {
            return Collections.emptyList();
        }
        return searchService.searchProducts(request);
    }

}

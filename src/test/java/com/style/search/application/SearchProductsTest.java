package com.style.search.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
import com.style.common.domain.PagedResponse;
import com.style.member.fixture.MemberFixture;
import com.style.product.domain.entity.Product;
import com.style.product.fixture.ProductFixture;
import com.style.search.fixture.SearchFixture;
import com.style.search.infra.SearchRepositoryImpl;
import com.style.search.presentation.request.SearchProductsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.style.product.domain.ProductCategory.SNEAKERS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchProductsTest {

    @Mock
    private SearchRepositoryImpl searchRepository;

    @InjectMocks
    private SearchService searchService;

    private List<Product> mockProducts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Brand mockBrand = BrandFixture.getMockBrand(MemberFixture.getMockMember());

        Product mockProduct1 = ProductFixture.getMockProduct(mockBrand);
        mockProduct1.setId(1L);

        Product mockProduct2 = ProductFixture.getMockProduct(mockBrand);
        mockProduct2.setId(2L);

        mockProducts = Arrays.asList(mockProduct1, mockProduct2);
    }

    @Test
    @DisplayName("searchProducts() - 성공")
    void getProductsSuccessTest() {
        // Given
        SearchProductsRequest request = SearchFixture.getSearchProductsRequest("sneakers", 1L, SNEAKERS);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Product> mockPage = new PageImpl<>(mockProducts, pageable, mockProducts.size());
        when(searchRepository.findProductsByConditions(request, pageable)).thenReturn(mockPage);

        // When
        PagedResponse<Product> result = searchService.searchProducts(request, pageable);

        // Then
        assertAll(
                () -> verify(searchRepository, times(1)).findProductsByConditions(request, pageable),
                () -> assertEquals(2, result.totalElements()),
                () -> assertEquals(1, result.totalPages()),
                () -> assertEquals(0, result.pageNumber()),
                () -> assertEquals(2, result.pageSize()),
                () -> assertEquals(mockProducts, result.elements())
        );
    }

    @Test
    @DisplayName("searchProducts() - 조건에 맞는 상품 없음")
    void getProductsNoResultsTest() {
        // Given
        SearchProductsRequest request = SearchFixture.getSearchProductsRequest("sneakers", 1L, SNEAKERS);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Product> emptyPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
        when(searchRepository.findProductsByConditions(request, pageable)).thenReturn(emptyPage);

        // When
        PagedResponse<Product> result = searchService.searchProducts(request, pageable);

        // Then
        assertAll(
                () -> verify(searchRepository, times(1)).findProductsByConditions(request, pageable),
                () -> assertEquals(0, result.totalElements()),
                () -> assertEquals(0, result.totalPages()),
                () -> assertTrue(result.elements().isEmpty())
        );
    }

    @Test
    @DisplayName("searchProducts() - Null 요청")
    void getProductsNullRequestTest() {
        // Given
        SearchProductsRequest request = new SearchProductsRequest();

        // When & Then
        assertTrue(request.hasNoSearchCriteria());
    }

}
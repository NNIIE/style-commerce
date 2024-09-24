package com.style.search.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
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
        when(searchRepository.findProductsByConditions(request)).thenReturn(mockProducts);

        // When
        List<Product> result = searchService.searchProducts(request);

        // Then
        assertAll(
                () -> verify(searchRepository, times(1)).findProductsByConditions(request),
                () -> assertEquals(2, result.size()),
                () -> assertEquals(mockProducts, result)
        );
    }

    @Test
    @DisplayName("searchProducts() - 조건에 맞는 상품 없음")
    void getProductsNoResultsTest() {
        // Given
        SearchProductsRequest request = SearchFixture.getSearchProductsRequest("sneakers", 1L, SNEAKERS);

        when(searchRepository.findProductsByConditions(request)).thenReturn(Collections.emptyList());

        // When
        List<Product> result = searchService.searchProducts(request);

        // Then
        assertAll(
                () -> verify(searchRepository, times(1)).findProductsByConditions(request),
                () -> assertTrue(result.isEmpty())
        );
    }

    @Test
    @DisplayName("searchProducts() - Null 요청")
    void getProductsNullRequestTest() {
        // Given
        SearchProductsRequest request = new SearchProductsRequest();

        // When & Then
        assertTrue(request.isRequestNull());
    }

}
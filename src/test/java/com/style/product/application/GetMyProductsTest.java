package com.style.product.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
import com.style.member.fixture.MemberFixture;
import com.style.product.domain.entity.Product;
import com.style.product.fixture.ProductFixture;
import com.style.product.infra.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetMyProductsTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Brand mockBrand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockBrand = BrandFixture.getMockBrand(MemberFixture.getMockMember());
    }

    @Test
    @DisplayName("getProductsByBrand() - 성공")
    void getProductsByBrandSuccessTest() {
        // Given
        List<Product> products = ProductFixture.getMockProducts();
        when(productRepository.findByBrandId(mockBrand.getId())).thenReturn(products);

        // When
        List<Product> result = productService.getProductsByBrand(mockBrand.getId());

        // Then
        assertAll(
                () -> verify(productRepository, times(1)).findByBrandId(mockBrand.getId()),
                () -> assertEquals(result.size(), 2),
                () -> assertEquals(result.get(0).getName(), ProductFixture.FIRST_PRODUCT_NAME),
                () -> assertEquals(result.get(1).getName(), ProductFixture.SECOND_PRODUCT_NAME)
        );
    }

    @Test
    @DisplayName("getProductsByBrand() - 상품 없음")
    void getProductsByBrandFailTest_NoProducts() {
        // Given
        when(productRepository.findByBrandId(mockBrand.getId())).thenReturn(Collections.emptyList());

        // When
        List<Product> result = productService.getProductsByBrand(mockBrand.getId());

        // Then
        assertAll(
                () -> assertTrue(result.isEmpty()),
                () -> verify(productRepository, times(1)).findByBrandId(mockBrand.getId())
        );
    }

}
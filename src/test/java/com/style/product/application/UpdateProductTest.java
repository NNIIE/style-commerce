package com.style.product.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
import com.style.common.exception.product.ProductException;
import com.style.member.fixture.MemberFixture;
import com.style.product.domain.ProductCategory;
import com.style.product.domain.entity.Product;
import com.style.product.fixture.ProductFixture;
import com.style.product.infra.repository.ProductRepository;
import com.style.product.presentation.request.UpdateProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateProductTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product mockProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Brand mockBrand = BrandFixture.getMockBrand(MemberFixture.getMockMember());
        mockProduct = ProductFixture.getMockProduct(mockBrand);
        mockProduct.setId(1L);
    }

    @Test
    @DisplayName("updateProduct() - 성공")
    void updateProductSuccessTest() {
        // Given
        UpdateProductRequest request = ProductFixture.getUpdateProductRequest(
                ProductCategory.SNEAKERS,
                "sneakers",
                BigDecimal.valueOf(200),
                20
        );
        when(productRepository.findById(mockProduct.getId())).thenReturn(Optional.of(mockProduct));

        // When
        productService.updateProduct(mockProduct.getId(), request);

        // Then
        assertAll(
                () -> verify(productRepository, times(1)).findById(mockProduct.getId()),
                () -> assertEquals(mockProduct.getCategory(), request.getCategory()),
                () -> assertEquals(mockProduct.getName(), request.getName()),
                () -> assertEquals(mockProduct.getPrice(), request.getPrice()),
                () -> assertEquals(mockProduct.getQuantity(), request.getQuantity())
        );
    }

    @Test
    @DisplayName("updateProduct() - 존재하지 않는 상품")
    void updateProductFailTest_ProductNotFound() {
        // Given
        UpdateProductRequest request = ProductFixture.getUpdateProductRequest(
                ProductCategory.SNEAKERS,
                "sneakers",
                BigDecimal.valueOf(200),
                20
        );
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        // When & Then
        assertAll(
                () -> assertThrows(ProductException.class, () -> productService.updateProduct(2L, request)),
                () -> verify(productRepository, times(1)).findById(2L),
                () -> verify(productRepository, never()).save(any(Product.class))
        );
    }

}

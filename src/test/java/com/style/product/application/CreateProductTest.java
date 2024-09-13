package com.style.product.application;

import com.style.brand.application.BrandService;
import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
import com.style.common.exception.brand.BrandException;
import com.style.common.exception.brand.BrandExceptionCode;
import com.style.member.fixture.MemberFixture;
import com.style.product.domain.ProductCategory;
import com.style.product.domain.entity.Product;
import com.style.product.fixture.ProductFixture;
import com.style.product.infra.ProductRepository;
import com.style.product.presentation.request.CreateProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateProductTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BrandService brandService;

    @InjectMocks
    private ProductService productService;

    private Brand mockBrand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockBrand = BrandFixture.getMockBrand(MemberFixture.getMockMember());
        mockBrand.setId(1L);
    }

    @Test
    @DisplayName("createProduct() - 성공")
    void createProductSuccessTest() {
        // Given
        CreateProductRequest request = ProductFixture.getCreateProductRequest(
                1L,
                ProductCategory.OUTER,
                "outer",
                BigDecimal.valueOf(100),
                10
        );

        when(brandService.getBrand(request.getBrandId())).thenReturn(mockBrand);
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        productService.createProduct(request);

        // Then
        assertAll(
                () -> verify(brandService, times(1)).getBrand(request.getBrandId()),
                () -> verify(productRepository, times(1)).save(any(Product.class)),
                () -> verify(productRepository).save(argThat(product ->
                        request.getName().equals(product.getName()) &&
                                request.getCategory().equals(product.getCategory()) &&
                                Objects.equals(request.getPrice(), product.getPrice()) &&
                                Objects.equals(request.getQuantity(), product.getQuantity()) &&
                                product.getBrand().equals(mockBrand)))
        );
    }

    @Test
    @DisplayName("createProduct() - 존재하지 않는 브랜드")
    void createProductFailTest_BrandNotFound() {
        // Given
        CreateProductRequest request = ProductFixture.getCreateProductRequest(
                1L,
                ProductCategory.OUTER,
                "outer",
                BigDecimal.valueOf(100),
                10
        );

        when(brandService.getBrand(1L)).thenThrow(new BrandException(BrandExceptionCode.BRAND_NOT_FOUND));

        // When & Then
        assertAll(
                () -> assertThrows(BrandException.class, () -> productService.createProduct(request)),
                () -> verify(brandService, times(1)).getBrand(1L),
                () ->verify(productRepository, never()).save(any(Product.class))
        );
    }

}

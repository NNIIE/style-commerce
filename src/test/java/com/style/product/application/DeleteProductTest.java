package com.style.product.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
import com.style.common.exception.product.ProductException;
import com.style.common.exception.product.ProductExceptionCode;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteProductTest {

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
    @DisplayName("deleteProduct() - 성공")
    void deleteProductSuccessTest() {
        // Given & When
        productService.deleteProduct(mockProduct.getId());

        // Then
        verify(productRepository, times(1)).deleteById(mockProduct.getId());
    }

    @Test
    @DisplayName("deleteProduct() - 존재하지 않는 상품")
    void deleteProductFailTest_ProductNotFound() {
        // Given
        doThrow(new ProductException(ProductExceptionCode.PRODUCT_NOT_FOUND))
                .when(productRepository).deleteById(mockProduct.getId());

        // When & Then
        assertAll(
                () -> assertThrows(ProductException.class, () -> productService.deleteProduct(mockProduct.getId())),
                () -> verify(productRepository, times(1)).deleteById(mockProduct.getId())
        );
    }

}

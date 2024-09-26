package com.style.search.application;

import com.style.search.domain.CategoryMinPriceAggregate;
import com.style.search.domain.dto.CategoryMinPriceProductDto;
import com.style.search.infra.SearchRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.style.product.domain.ProductCategory.BAG;
import static com.style.product.domain.ProductCategory.SNEAKERS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FindLowestProductsByCategoryAndTotalPriceTest {

    @Mock
    private SearchRepositoryImpl searchRepository;

    @InjectMocks
    private SearchService searchService;

    private List<CategoryMinPriceProductDto> mockDtos;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        CategoryMinPriceProductDto dto1 = new CategoryMinPriceProductDto(SNEAKERS, 1L, BigDecimal.valueOf(10000));
        CategoryMinPriceProductDto dto2 = new CategoryMinPriceProductDto(BAG, 2L, BigDecimal.valueOf(20000));

        mockDtos = Arrays.asList(dto1, dto2);
    }

    @Test
    @DisplayName("getLowestProductsByCategoryAndTotalPrice() - 성공")
    void getLowestProductsSuccessTest() {
        // Given
        when(searchRepository.findLowestProductsByCategoryAndTotalPrice()).thenReturn(mockDtos);

        // When
        CategoryMinPriceAggregate result = searchService.findLowestProductsByCategoryAndTotalPrice();

        // Then
        assertAll(
                () -> verify(searchRepository, times(1)).findLowestProductsByCategoryAndTotalPrice(),
                () -> assertEquals(2, result.getProducts().size()),
                () -> assertEquals(SNEAKERS, result.getProducts().get(0).getCategory()),
                () -> assertEquals(BigDecimal.valueOf(10000), result.getProducts().get(0).getPrice())
        );
    }

}

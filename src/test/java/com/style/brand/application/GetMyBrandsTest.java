package com.style.brand.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
import com.style.brand.infra.repository.BrandRepository;
import com.style.member.domain.entity.Member;
import com.style.member.fixture.MemberFixture;
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

class GetMyBrandsTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    private Member mockMember;

    private List<Brand> mockBrands;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMember = MemberFixture.getMockMember();
        mockBrands = BrandFixture.getMockBrands();
    }

    @Test
    @DisplayName("getMyBrands() - 성공")
    void getMyBrandsSuccessTest() {
        // Given
        when(brandRepository.findByOwnerId(mockMember.getId())).thenReturn(mockBrands);

        // When
        List<Brand> brands = brandService.getMyBrands(mockMember.getId());

        // Then
        assertAll(
                () -> verify(brandRepository, times(1)).findByOwnerId(mockMember.getId()),
                () -> assertEquals(2, brands.size()),
                () -> assertEquals(BrandFixture.FIRST_BRAND_NAME, brands.get(0).getName()),
                () -> assertEquals(BrandFixture.SECOND_BRAND_NAME, brands.get(1).getName())
        );
    }

    @Test
    @DisplayName("getMyBrands() - 브랜드 없음")
    void getMyBrandsNoBrandsTest() {
        // Given
        when(brandRepository.findByOwnerId(mockMember.getId())).thenReturn(Collections.emptyList());

        // When
        List<Brand> brands = brandService.getMyBrands(mockMember.getId());

        // Then
        assertAll(
                () -> assertTrue(brands.isEmpty()),
                () -> verify(brandRepository, times(1)).findByOwnerId(mockMember.getId())
        );
    }

}
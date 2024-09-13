package com.style.brand.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
import com.style.brand.infra.repository.BrandRepository;
import com.style.brand.presentation.request.UpdateBrandRequest;
import com.style.common.exception.brand.BrandException;
import com.style.member.domain.entity.Member;
import com.style.member.fixture.MemberFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateBrandTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    private Member mockMember;

    private Brand mockBrand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMember = MemberFixture.getMockMember();
        mockBrand = BrandFixture.getMockBrand(mockMember);
        mockBrand.setId(1L);
    }

    @Test
    @DisplayName("updateBrand() - 성공")
    void updateBrandSuccessTest() {
        // Given
        UpdateBrandRequest request = BrandFixture.getUpdateBrandRequest("UpdatedBrand", 654321L);

        when(brandRepository.findById(mockBrand.getId())).thenReturn(Optional.of(mockBrand));

        // When
        brandService.updateBrand(mockBrand.getId(), request);

        // Then
        assertAll(
                () -> verify(brandRepository, times(1)).findById(mockBrand.getId()),
                () -> assertEquals(mockBrand.getName(), request.getName()),
                () -> assertEquals(mockBrand.getPhoneNumber(), request.getPhoneNumber())
        );
    }

    @Test
    @DisplayName("updateBrand() - 존재하지 않는 브랜드")
    void updateBrandBrandNotFoundTest() {
        // Given
        UpdateBrandRequest request = BrandFixture.getUpdateBrandRequest("UpdatedBrand", 654321L);

        when(brandRepository.findById(2L)).thenReturn(Optional.empty());

        // When & Then
        assertAll(
                () -> assertThrows(BrandException.class, () -> brandService.updateBrand(2L, request)),
                () -> verify(brandRepository, times(1)).findById(2L)
        );
    }

}

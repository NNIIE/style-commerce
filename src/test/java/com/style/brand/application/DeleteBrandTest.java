package com.style.brand.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
import com.style.brand.infra.repository.BrandRepository;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteBrandTest {

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
    @DisplayName("deleteBrand() - 성공")
    void deleteBrandSuccessTest() {
        // Given
        when(brandRepository.findByOwnerIdAndId(mockMember.getId(), mockBrand.getId())).thenReturn(Optional.of(mockBrand));

        // When
        brandService.deleteBrand(mockMember.getId(), mockBrand.getId());

        // Then
        assertAll(
                () -> verify(brandRepository, times(1)).findByOwnerIdAndId(mockMember.getId(), mockBrand.getId()),
                () -> verify(brandRepository, times(1)).delete(mockBrand)

        );
    }

    @Test
    @DisplayName("deleteBrand() - 존재하지 않는 브랜드")
    void deleteBrandFailTest_BrandNotFound() {
        // Given
        when(brandRepository.findByOwnerIdAndId(mockMember.getId(), 2L)).thenReturn(Optional.empty());

        // When & Then
        assertAll(
                () -> assertThrows(BrandException.class, () -> brandService.deleteBrand(mockMember.getId(), 2L)),
                () -> verify(brandRepository, times(1)).findByOwnerIdAndId(mockMember.getId(), 2L),
                () -> verify(brandRepository, never()).delete(any(Brand.class))
        );
    }

}

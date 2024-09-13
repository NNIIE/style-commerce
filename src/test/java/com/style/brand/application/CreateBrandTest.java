package com.style.brand.application;

import com.style.brand.domain.entity.Brand;
import com.style.brand.fixture.BrandFixture;
import com.style.brand.infra.repository.BrandRepository;
import com.style.brand.presentation.request.CreateBrandRequest;
import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import com.style.member.application.MemberService;
import com.style.member.domain.entity.Member;
import com.style.member.fixture.MemberFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateBrandTest {

    @Mock
    private MemberService memberService;

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
    }

    @Test
    @DisplayName("createBrand() - 성공")
    void createBrandSuccessTest() {
        // Given
        CreateBrandRequest request = BrandFixture.getCreateBrandRequest("NewBrand", 123456L, 123456L);

        when(memberService.getMember(mockMember.getId())).thenReturn(mockMember);
        when(brandRepository.save(any(Brand.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        brandService.createBrand(mockMember.getId(), request);

        // Then
        assertAll(
                () -> verify(memberService, times(1)).getMember(mockMember.getId()),
                () -> verify(brandRepository, times(1)).save(any(Brand.class)),
                () -> verify(brandRepository).save(argThat(brand ->
                        brand.getOwner().equals(mockMember) &&
                                brand.getName().equals(request.getName()) &&
                                brand.getLicenseNumber().equals(request.getLicenseNumber()) &&
                                brand.getPhoneNumber().equals(request.getPhoneNumber())))
        );

    }

    @Test
    @DisplayName("createBrand() - 존재하지 않는 회원")
    void createBrandMemberNotFoundTest() {
        // Given
        CreateBrandRequest request = BrandFixture.getCreateBrandRequest("NewBrand", 123456L, 123456L);
        when(memberService.getMember(mockMember.getId()))
                .thenThrow(new MemberException(MemberExceptionCode.MEMBER_NOT_FOUNT));

        // When & Then
        assertAll(
                () -> assertThrows(MemberException.class, () -> brandService.createBrand(mockMember.getId(), request)),
                () -> verify(memberService, times(1)).getMember(mockMember.getId()),
                () -> verify(brandRepository, never()).save(any(Brand.class))
        );
    }

}

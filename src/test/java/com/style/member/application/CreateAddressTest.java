package com.style.member.application;

import com.style.common.exception.member.MemberException;
import com.style.member.domain.entity.Member;
import com.style.member.fixture.AddressFixture;
import com.style.member.fixture.MemberFixture;
import com.style.member.infra.repository.MemberRepository;
import com.style.member.presentation.request.CreateAddressRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateAddressTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member mockMember;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMember = MemberFixture.getMockMember();
    }

    @Test
    @DisplayName("createAddress() - 성공")
    void createAddressSuccessTest() {
        // Given
        CreateAddressRequest request = AddressFixture.getCreateAddressRequest("Province", "City", "District");
        when(memberRepository.findMemberWithAddressesById(mockMember.getId())).thenReturn(Optional.of(mockMember));

        // When
        memberService.createAddress(mockMember.getId(), request);

        // Then
        assertAll(
                () -> verify(memberRepository, times(1)).findMemberWithAddressesById(mockMember.getId()),
                () -> assertEquals(mockMember.getAddresses().size(), 1),
                () -> assertEquals(mockMember.getAddresses().get(0).getProvince(), request.getProvince()),
                () -> assertEquals(mockMember.getAddresses().get(0).getCity(), request.getCity()),
                () -> assertEquals(mockMember.getAddresses().get(0).getDistrict(), request.getDistrict())
        );
    }

    @Test
    @DisplayName("createAddress() - 존재하지 않는 회원")
    void createAddressMemberNotFoundTest() {
        // Given
        when(memberRepository.findMemberWithAddressesById(mockMember.getId())).thenReturn(Optional.empty());
        CreateAddressRequest request = new CreateAddressRequest("Province", "City", "District");

        // When & Then
        assertAll(
                () -> assertThrows(MemberException.class, () -> memberService.createAddress(mockMember.getId(), request)),
                () -> verify(memberRepository, times(1)).findMemberWithAddressesById(mockMember.getId())
        );
    }

}

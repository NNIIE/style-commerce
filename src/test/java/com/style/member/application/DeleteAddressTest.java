package com.style.member.application;

import com.style.common.exception.member.MemberException;
import com.style.member.domain.entity.Address;
import com.style.member.domain.entity.Member;
import com.style.member.fixture.AddressFixture;
import com.style.member.fixture.MemberFixture;
import com.style.member.infra.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteAddressTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member mockMember;

    private Address mockAddress;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMember = MemberFixture.getMockMember();

        mockAddress = AddressFixture.getMockAddress(mockMember);
        mockAddress.setId(1L);

        mockMember.getAddresses().add(mockAddress);
    }

    @Test
    @DisplayName("deleteAddress() - 성공")
    void deleteAddressSuccessTest() {
        // Given
        when(memberRepository.findMemberWithAddressesById(mockMember.getId())).thenReturn(Optional.of(mockMember));

        // When
        memberService.deleteAddress(mockMember.getId(), mockAddress.getId());

        // Then
        assertAll(
                () -> verify(memberRepository, times(1)).findMemberWithAddressesById(mockMember.getId()),
                () -> assertTrue(mockMember.getAddresses().isEmpty())
        );
    }

    @Test
    @DisplayName("deleteAddress() - 존재하지 않는 회원")
    void deleteAddressMemberNotFoundTest() {
        // Given
        when(memberRepository.findMemberWithAddressesById(mockMember.getId())).thenReturn(Optional.empty());

        // When & Then
        assertAll(
                () -> assertThrows(MemberException.class, () -> memberService.deleteAddress(mockMember.getId(), 1L)),
                () -> verify(memberRepository, times(1)).findMemberWithAddressesById(mockMember.getId())
        );
    }

}

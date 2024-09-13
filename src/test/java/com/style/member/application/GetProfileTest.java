package com.style.member.application;

import com.style.common.exception.member.MemberException;
import com.style.member.domain.entity.Member;
import com.style.member.fixture.MemberFixture;
import com.style.member.infra.repository.MemberRepository;
import com.style.member.presentation.response.MemberProfileResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetProfileTest {

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
    @DisplayName("getProfile() - 성공")
    void getProfileSuccessTest() {
        // Given
        when(memberRepository.findMemberWithAddressesById(mockMember.getId())).thenReturn(Optional.of(mockMember));

        // When
        MemberProfileResponse profileResponse = memberService.getProfile(mockMember.getId());

        // Then
        assertAll(
                () -> assertNotNull(profileResponse),
                () -> assertEquals(mockMember.getNickname(), profileResponse.nickname()),
                () -> assertEquals(mockMember.getEmail(), profileResponse.email()),
                () -> verify(memberRepository, times(1)).findMemberWithAddressesById(mockMember.getId())
        );
    }

    @Test
    @DisplayName("getProfile() - 존재하지 않는 회원")
    void getProfileMemberNotFoundTest() {
        // Given
        when(memberRepository.findMemberWithAddressesById(mockMember.getId())).thenReturn(Optional.empty());

        // When & Then
        assertAll(
                () -> assertThrows(MemberException.class, () -> memberService.getProfile(mockMember.getId())),
                () -> verify(memberRepository, times(1)).findMemberWithAddressesById(mockMember.getId())
        );
    }

}
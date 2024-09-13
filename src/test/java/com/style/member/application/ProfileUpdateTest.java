package com.style.member.application;

import com.style.common.exception.member.MemberException;
import com.style.member.domain.entity.Member;
import com.style.member.fixture.MemberFixture;
import com.style.member.infra.encrypt.PasswordEncoder;
import com.style.member.infra.repository.MemberRepository;
import com.style.member.presentation.request.UpdateMemberRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfileUpdateTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    private Member mockMember;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMember = MemberFixture.getMockMember();
    }

    @Test
    @DisplayName("profileUpdate() - 성공")
    void profileUpdateSuccessTest() {
        // Given
        UpdateMemberRequest request = MemberFixture.getUpdateMemberRequest("newNickname", "newPassword");
        when(memberRepository.findById(mockMember.getId())).thenReturn(Optional.of(mockMember));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedPassword");

        // When
        memberService.profileUpdate(mockMember.getId(), request);

        // Then
        assertAll(
                () -> verify(memberRepository, times(1)).findById(mockMember.getId()),
                () -> verify(memberRepository, times(1)).existsByNickname(request.getNickname()),
                () -> verify(passwordEncoder, times(1)).encode(request.getPassword()),
                () -> assertEquals(mockMember.getNickname(), request.getNickname()),
                () -> assertEquals(mockMember.getPassword(), "encodedPassword")
        );
    }

    @Test
    @DisplayName("profileUpdate() - 닉네임 중복")
    void profileUpdateMemberDuplicateTest() {
        // Given
        UpdateMemberRequest request = MemberFixture.getUpdateMemberRequest("newNickname", "newPassword");
        when(memberRepository.findById(mockMember.getId())).thenReturn(Optional.of(mockMember));
        when(memberRepository.existsByNickname(request.getNickname())).thenReturn(true);

        // When & Then
        assertAll(
                () -> assertThrows(MemberException.class, () -> memberService.profileUpdate(mockMember.getId(), request)),
                () -> verify(memberRepository, times(1)).findById(mockMember.getId()),
                () -> verify(memberRepository, times(1)).existsByNickname(request.getNickname())
        );
    }

}

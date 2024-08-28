package com.style.member.application;

import com.core.entity.member.Member;
import com.style.member.infra.MemberRepository;
import com.style.member.presentation.request.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public void signUp(final MemberSignUpRequest request) {
        final Member member = Member.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAdmin(request.getIsAdmin())
                .build();

        memberRepository.save(member);
    }

}

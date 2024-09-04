package com.style.member.application;

import com.style.common.domain.entity.Member;
import com.style.member.infra.MemberRepository;
import com.style.member.presentation.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(final SignUpRequest request) {
        final Member member = Member.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAdmin(request.getIsAdmin())
                .build();

        memberRepository.save(member);
    }

}

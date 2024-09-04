package com.style.member.application;

import com.style.common.domain.entity.Member;
import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import com.style.member.infra.encrypt.PasswordEncoder;
import com.style.member.infra.repository.MemberRepository;
import com.style.member.presentation.request.SignOffRequest;
import com.style.member.presentation.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
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

    @Transactional
    public void signOff(final Member member, final SignOffRequest request) {
        verifyPassword(request.getPassword(), member.getPassword());
        memberRepository.delete(member);
    }

    private void verifyPassword(final String requestPassword, final String memberPassword) {
        if (!passwordEncoder.verifyPassword(requestPassword, memberPassword)) {
            throw new MemberException(MemberExceptionCode.INVALID_CREDENTIALS);
        }
    }

}

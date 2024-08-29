package com.auth.member.application;

import com.auth.common.exception.AuthException;
import com.auth.common.exception.AuthExceptionCode;
import com.auth.member.infra.AuthMemberRepository;
import com.auth.member.presentation.request.MemberSignInRequest;
import com.core.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthMemberService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthMemberRepository authMemberRepository;

    @Transactional(readOnly = true)
    public Member signIn(final MemberSignInRequest request) {
        final Member member = authMemberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException(AuthExceptionCode.MEMBER_NOT_FOUNT));

        verifyPassword(request.getPassword(), member.getPassword());

        return member;
    }

    private void verifyPassword(final String requestPassword, final String memberPassword) {
        if (!passwordEncoder.matches(requestPassword, memberPassword)) {
            throw new AuthException(AuthExceptionCode.INVALID_CREDENTIALS);
        }
    }

}

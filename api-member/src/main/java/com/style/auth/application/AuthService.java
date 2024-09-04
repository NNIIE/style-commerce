package com.style.auth.application;

import com.style.auth.infra.AuthRepository;
import com.style.auth.presentation.request.SignInRequest;
import com.style.common.domain.entity.Member;
import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import com.style.member.infra.encrypt.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;

    @Transactional(readOnly = true)
    public Member signIn(final SignInRequest request) {
        final Member member = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(MemberExceptionCode.MEMBER_NOT_FOUNT));

        verifyPassword(request.getPassword(), member.getPassword());

        return member;
    }

    private void verifyPassword(final String requestPassword, final String memberPassword) {
        if (!passwordEncoder.verifyPassword(requestPassword, memberPassword)) {
            throw new MemberException(MemberExceptionCode.INVALID_PASSWORD);
        }
    }

}

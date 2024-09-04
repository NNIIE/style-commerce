package com.style.auth.application;

import com.style.common.exception.member.MemberException;
import com.style.common.exception.member.MemberExceptionCode;
import com.style.common.domain.entity.Member;
import com.style.auth.infra.AuthRepository;
import com.style.auth.presentation.request.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;

    @Transactional(readOnly = true)
    public Member signIn(final SignInRequest request) {
        final Member member = authRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(MemberExceptionCode.MEMBER_NOT_FOUNT));

        verifyPassword(request.getPassword(), member.getPassword());

        return member;
    }

    private void verifyPassword(final String requestPassword, final String memberPassword) {
        if (!passwordEncoder.matches(requestPassword, memberPassword)) {
            throw new MemberException(MemberExceptionCode.INVALID_CREDENTIALS);
        }
    }

}

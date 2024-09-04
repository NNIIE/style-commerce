package com.style.fixture;

import com.style.auth.presentation.request.SignInRequest;
import com.style.member.presentation.request.SignOffRequest;
import com.style.member.presentation.request.SignUpRequest;

public class MemberFixture {

    public static SignUpRequest createSignUpRequest(String email, String nickname, String password, Boolean isAdmin) {
        return new SignUpRequest(email, nickname, password, isAdmin);
    }

    public static SignInRequest createSignInRequest(String email, String password) {
        return new SignInRequest(email, password);
    }

}

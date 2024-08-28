package com.style.member.fixture;

import com.style.member.presentation.request.MemberSignUpRequest;

public class MemberFixture {

    public static MemberSignUpRequest createSignUpRequest(String email, String nickname, String password, Boolean isAdmin) {
        return new MemberSignUpRequest(email, nickname, password, isAdmin);
    }

}

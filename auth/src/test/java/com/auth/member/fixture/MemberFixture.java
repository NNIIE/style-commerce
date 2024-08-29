package com.auth.member.fixture;

import com.auth.member.presentation.request.MemberSignInRequest;

public class MemberFixture {

    public static MemberSignInRequest createSignInRequest(String email, String password) {
        return new MemberSignInRequest(email, password);
    }

}

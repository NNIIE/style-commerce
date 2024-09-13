package com.style.member.fixture;

import com.style.member.domain.MemberRole;
import com.style.member.domain.entity.Member;
import com.style.member.presentation.request.SignInRequest;
import com.style.member.presentation.request.SignUpRequest;
import com.style.member.presentation.request.UpdateMemberRequest;

public class MemberFixture {

    public static Member getMockMember() {
        return Member.builder()
                .email("test@naver.com")
                .nickname("testNickname")
                .password("testPassword")
                .role(MemberRole.ADMIN)
                .build();
    }

    public static SignUpRequest getSignUpRequest(String email, String nickname, String password, MemberRole role) {
        return new SignUpRequest(email, nickname, password, role);
    }

    public static SignInRequest getSignInRequest(String email, String password) {
        return new SignInRequest(email, password);
    }

    public static UpdateMemberRequest getUpdateMemberRequest(String nickname, String password) {
        return new UpdateMemberRequest(nickname, password);
    }

}

package com.style.member.presentation.request;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

import static com.style.member.utils.MemberConst.*;

@Getter
@ToString
public class UpdateRequest {

    @Size(max = 10, message = "닉네임은 10글자 이하이어야 합니다.")
    @Pattern(regexp = MEMBER_NICKNAME_REGEXP, message = "닉네임은 한글 또는 영어이어야 합니다.")
    private String nickname;

    @Pattern(regexp = MEMBER_PASSWORD_REGEXP, message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자 형식이어야 합니다.")
    private String password;

    public boolean isNicknameUpdate() {
        return !StringUtils.isEmpty(nickname);
    }

    public boolean isPasswordUpdate() {
        return !StringUtils.isEmpty(password);
    }

}

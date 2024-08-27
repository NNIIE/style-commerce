package com.style.member.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberConst {

    public static final String USER_EMAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    public static final String USER_NICKNAME_REGEXP = "^[가-힣a-zA-Z]+$";
    public static final String USER_PASSWORD_REGEXP = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,16}";

}

package com.style.member.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MemberConst {

    public static final String SESSION_MEMBER_KEY = "member";
    public static final String MEMBER_EMAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    public static final String MEMBER_NICKNAME_REGEXP = "^[가-힣a-zA-Z]+$";
    public static final String MEMBER_PASSWORD_REGEXP = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,16}";
    public static final String ADDRESS_REGEXP = "^[가-힣]+$";

}
